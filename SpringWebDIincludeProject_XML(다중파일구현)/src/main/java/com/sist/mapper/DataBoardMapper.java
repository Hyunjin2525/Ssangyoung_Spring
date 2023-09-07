package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.DataBoardVo;

public interface DataBoardMapper {
	@Select("SELECT no,subject,name,TO_CHAR(regdate, 'YYYY-MM-DD') as dbday,hit,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			+ "FROM(SELECT /*+INDEX_DESC(springDataBoard sdb_no_pk)*/no,subject,name,regdate,hit FROM springDataBoard)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVo> databoardListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springDataBoard")
	public int databoardTotalpage();
	
	@SelectKey(keyProperty = "no", resultType = int.class, before = true, statement = "SELECT NVL(MAX(no)+1,1) as no FROM springDataBoard")
	@Insert("INSERT INTO springDataBoard(no,name,subject,content,pwd,filename,filesize,filecount) "
			+ "VALUES(#{no},#{name},#{subject},#{content},#{pwd},#{filename},#{filesize},#{filecount})")
	public void databoardInsert(DataBoardVo vo);
	
	//상세보기
	@Update("UPDATE springDataBoard SET hit=hit+1 WHERE no=#{no}")
	public void dataBoardHit(int no);
	
	@Select("SELECT no,subject,name,content,TO_CHAR(regdate, 'YYYY-MM-DD') as dbday,filename,filesize,filecount,hit FROM springDataBoard WHERE no=#{no}")
	public DataBoardVo databoardDetailData(int no);
	//수정하기
	
	//삭제하기
	
	//검색
	@Select("SELECT no,subject,name,TO_CHAR(regdate, 'YYYY-MM-DD') as dbday,hit FROM springDataBoard "
			+ "WHERE ${fs} LIKE '%'||#{ss}||'%'")
	//$=> 컬럼명,테이블명, #=> 일반 데이터
	public List<DataBoardVo> databoardFindData(Map map);
	
	//비밀번호 검색
	@Select("SELECT pwd FROM springDataBoard WHERE no=#{no}")
	public String databoardPWD(int no);
	
	@Update("UPDATE springDataBoard SET subject=#{subject},content=#{content},name=#{name} WHERE no=#{no}")
	public void databoardUpdate(DataBoardVo vo);
	
	@Delete("DELETE FROM springDataBoard WHERE no=#{no}")
	public void databoardDelete(int no);
	
}
