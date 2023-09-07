package com.sist.dao;
import com.sist.vo.*;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class FoodDAO extends SqlSessionDaoSupport{

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	/*
	 * 	<select id="categoryListData" resultType="com.sist.vo.CategoryVO" parameterType="hashmap">
 	SELECT cno,title,poster,subject
 	FROM food_category WHERE 
 	<if test="cno==1"> <!-- Map의 키 이름 -->
 	cno BETWEEN 1 AND 12	
 	</if>
 	<if test="cno==2">
 	cno BETWEEN 13 AND 18	
 	</if>
 	<if test="cno==3">
 	cno BETWEEN 19 AND 30	
 	</if>
	 * 
	 */
	public List<CategoryVO> categoryListData(Map map)
	{
		return getSqlSession().selectList("categoryListData",map);
	}

	
}
