package com.sist.main;
import java.io.File;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.collections.map.HashedMap;

public class ClassPathApplicationContext implements AppplicationContext{
	private Map clsMap=new HashMap();
	public ClassPathApplicationContext(String path) {
		try {
			SAXParserFactory spf=SAXParserFactory.newInstance();
			SAXParser sp=spf.newSAXParser();
			XMLParse xm=new XMLParse();
			//sp.parse(new File(),xm);
			clsMap=xm.getMap();
		} catch (Exception e) {}
	}
	@Override
	public Object getBean(String key) {
		// TODO Auto-generated method stub
		return clsMap.get(key);
	}

}
