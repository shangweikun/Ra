package com.example.mac._1.demo.person;

import com.example.mac._1.demo.HashRedisKV;
import com.example.mac._1.demo.person.ro.BasicRO;
import com.example.mac._1.demo.person.ro.CertInfoRO;
import com.example.mac._1.demo.person.ro.ThemeRO;

public class PersonVO {

	private final HashRedisKV<PersonVO> KV;

	protected PersonVO(String key) {
		this.KV = new HashRedisKV<>("MANAGER", key, this);
	}

	private volatile BasicRO basic;

	public BasicRO getBasic() {
		return this.basic;
	}

	protected void setBasic(BasicRO basic) {
		this.basic = basic;
	}

	private volatile CertInfoRO certInfoRO;

	public CertInfoRO getCertInfoRO() {
		if (this.certInfoRO == null) {
			return PersonHandler.CertInfoHandler.getValue(KV);
		}
		return this.certInfoRO;
	}

	protected void setCertInfoRO(CertInfoRO certInfoRO) {
		this.certInfoRO = certInfoRO;
	}

	private volatile ThemeRO themeRO;

	public ThemeRO getThemeRO() {

		return this.themeRO;
	}

	protected boolean themeIsNull() {
		return themeRO == null;
	}

	protected void setThemeRO(ThemeRO themeRO) {
		this.themeRO = themeRO;
	}
}
