package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StudentDAO extends SqlSessionDaoSupport{
	//목록 읽기
	/*<select id="studentListData" resultType="StudentVO">
 		SELECT * FROM student
 	</select>
*/	
	public List<StudentVO> studentListData()
	{
		return getSqlSession().selectList("studentListData");
	}
	//상세보기
	public StudentVO studentDetail(int hakbun)
	{
		return getSqlSession().selectOne("studentDetail",hakbun);
	}
	//검색
	public List<StudentVO> studentFindData(String name)
	{
		return getSqlSession().selectList("studentFindData",name);
	}
	//추가
	public void studentInsert(StudentVO vo)
	{
		getSqlSession().insert("studentInsert",vo);
	}
	//수정
	public void studentUpdate(StudentVO vo)
	{
		getSqlSession().update("studentUpdate",vo);
	}
	//삭제
	public void studentDelete(int hakbun)
	{
		getSqlSession().delete("studentDelete",hakbun);
	}
}
