package com.sist.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FoodDAO extends SqlSessionDaoSupport{
	public List<FoodVO> foodListData(Map map)
	{
		return getSqlSession().selectList("foodCategoryList",map);
	}
}
