package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface BoardMapper {
	//목록 출력
	@Select("SELECT no,subject,name,TO_CHAR(regdate, 'YYYY-MM-DD') as dbday,hit,num "
			+ "FROM (SELECT /*+ INDEX_DESC(springBoard sb_no_pk)*/no,subject,name,regdate,hit,rownum as num FROM (SELECT no,subject,name,regdate,hit "
			+ "FROM springBoard)) WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map); //메개변수는 한개만 쓸 수 있다
	
	//총 페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springBoard")
	public int boardTotalpage();
	@Insert("INSERT INTO springBoard(no,name,subject,content,pwd) VALUES(sb_no_seq.nextval,#{name},#{subject},"
			+ "#{content},#{pwd})")
	public void boardInsert(BoardVO vo);
	
	@Update("UPDATE springBoard SET hit=hit+1 WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday FROM springBoard WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	//수정하기
	@Select("SELECT no,name,subject,content FROM springBoard WHERE no=#{no}")
	public BoardVO boardUpdateData(int no);

	@Select("SELECT pwd FROM springBoard WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE springBoard SET name=#{name},subject=#{subject},content=#{content} WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	@Delete("DELETE FROM springBoard WHERE no=#{no}")
	public void boardDelete(int no);

}
