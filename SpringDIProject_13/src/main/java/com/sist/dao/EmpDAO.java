package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;
/*
 * <sql id="select-emp">
  	SELECT empno,ename,job,TO_CHAR(hiredate, 'YYY-MM_DD') as dbday,sal,dname,loc
  	FROM emp,dept
  	WHERE emp.deptno=dept.deptno
  </sql>
  <select id="empdeptListData" resultMap="empMap">
   <include refid="select-emp"/>
  	ORDER BY sal DESC
  </select>
  <select id="empdeptDetailDate" resultMap="empMap" parameterType="int">
  <include refid="select-emp"></include>
  	AND empno=#{empno}
  </select>
 */

import com.sist.vo.EmpVO;
public class EmpDAO extends SqlSessionDaoSupport{
	public List<EmpVO> empdeptListData()
	{
		return getSqlSession().selectList("empdeptListData");
	}
	public EmpVO empdeptDetailData(int empno)
	{
		return getSqlSession().selectOne("empdeptDetailDate",empno);
	}
}
