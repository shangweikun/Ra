package com.example.mac._1.demo.person;

import com.example.mac._1.demo.DefaultHandler;
import com.example.mac._1.demo.HashRedisKV;
import com.example.mac._1.demo.PersonDAO;
import com.example.mac._1.demo.RedisUtil;
import com.example.mac._1.demo.person.ro.BasicRO;
import com.example.mac._1.demo.person.ro.CertInfoRO;
import com.example.mac._1.demo.person.ro.ThemeRO;

public class PersonHandler {

	private static final PersonDAO dao = new PersonDAO() {
		@Override
		public BasicRO selectBasicByKey(String key) {
			return new BasicRO();
		}

		@Override
		public CertInfoRO selectCertInfoByKey(String key) {
			return new CertInfoRO();
		}

		@Override
		public ThemeRO selectThemeByKey(String key) {
			return new ThemeRO();
		}
	};

	public static void init(HashRedisKV<PersonVO> KV) {
		BasicHandler.getValue(KV);
	}

	public static void refresh(HashRedisKV<PersonVO> KV) {
		BasicHandler.setValue(KV);
	}

	protected static class BasicHandler {

		protected static BasicRO getValue(HashRedisKV<PersonVO> KV) {

			synchronized (KV.value) {
				Object obj = KV.hGet("basic");
				if (obj == null) {
					setValue(KV);
				}
				KV.value.setBasic((BasicRO) obj);
			}
			return KV.value.getBasic();
		}

		protected static void setValue(HashRedisKV<PersonVO> KV) {
			BasicRO result;
			KV.value.setBasic(result = dao.selectBasicByKey(KV.prefix + KV.key));
			KV.hSet("basic", result);
		}
	}

	protected static DefaultHandler<CertInfoRO, PersonVO> certInfoRODefaultHandler = new DefaultHandler<>(
			dao::selectCertInfoByKey, PersonVO::getCertInfoRO, PersonVO::setCertInfoRO, "certInfo"
	);

	protected static class CertInfoHandler {

		protected static CertInfoRO getValue(HashRedisKV<PersonVO> KV) {
			return certInfoRODefaultHandler.getValue(KV);
		}

		protected static void setValue(HashRedisKV<PersonVO> KV) {
			certInfoRODefaultHandler.setValue(KV);
		}
	}

	protected static class ThemeHandler {

		protected static ThemeRO getValue(HashRedisKV<PersonVO> KV) {
			synchronized (KV.value) {
				if (KV.value.themeIsNull()) {
					String key = KV.prefix + KV.key;
					KV.value.setThemeRO((ThemeRO) RedisUtil.hGet(key, "certInfo"));
				}
			}
			return KV.value.getThemeRO();
		}

		protected static void setValue(HashRedisKV<PersonVO> KV) {
			KV.value.setThemeRO(dao.selectThemeByKey(KV.key));
		}
	}
}
