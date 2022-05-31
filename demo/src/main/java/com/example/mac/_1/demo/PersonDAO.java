package com.example.mac._1.demo;

import com.example.mac._1.demo.person.ro.BasicRO;
import com.example.mac._1.demo.person.ro.CertInfoRO;
import com.example.mac._1.demo.person.ro.ThemeRO;

public abstract class PersonDAO {
	public abstract BasicRO selectBasicByKey(String key);

	public abstract CertInfoRO selectCertInfoByKey(String key);

	public abstract ThemeRO selectThemeByKey(String key);
}
