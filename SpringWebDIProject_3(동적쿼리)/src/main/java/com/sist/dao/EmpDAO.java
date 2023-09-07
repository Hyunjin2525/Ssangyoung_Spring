package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
/*
 *  CONSTRUCTOR, FIELD, METHOD, PARAMETER
 * 
 */
@Repository
public class EmpDAO extends SqlSessionDaoSupport{
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/*
	 * 
	 * <select id="empGetNameData" resultType="String">
 		SELECT ename FROM emp
 	</select>
 	<select id="empInfoData" resultType="EmpVO" parameterType="hashmap">
 		SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal,comm,deptno
 		FROM emp
	 */
	public List<String> empGetNameData()
	{
		return getSqlSession().selectList("empGetNameData");
	}
	
	public List<EmpVO> empInfoDate(Map map)
	{
		return getSqlSession().selectList("empInfoData",map);
	}
}
