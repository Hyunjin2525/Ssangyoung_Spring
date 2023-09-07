package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.BoardMapper;
import com.sist.vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	//@Select("SELECT no,subject,name,TO_CHAR(regdate, 'YYYY-MM-DD'),hit,num "
			//+ "FROM (SELECT /*+ INDEX_DESC(springBoard sb_no_pk)*/no,subject,name,regdate,hit,rownum as num FROM (SELECT no,subject,name,regdate,hit "
		//	+ "FROM springBoard)) WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map) //메개변수는 한개만 쓸 수 있다
	{
		return mapper.boardListData(map);
	}
	
	//@Select("SELECT CEIL(COUNT(*)/10.0) FROM springBoard")
	public int boardTotalpage()
	{
		return mapper.boardTotalpage();
	}
	
	//@Insert("INSERT INTO springBoard(no,name,subject,content,pwd) VALUES(sb_no_seq.nextval,#{name},#{subject},"
	//		+ "#{content},#{pwd})")
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	
	//@Select("SELECT * FROM springBoard WHERE no=#{no}")
	public BoardVO boardDetailData(int no)
	{
		mapper.hitIncrement(no); //조회수 증가 후
		return mapper.boardDetailData(no);          // sql문장 두개 사용할 때
	}
	
	//@Select("SELECT no,name,subject,content FROM springBoard WHERE no=?")
	public BoardVO boardUpdateData(int no)
	{
		return mapper.boardUpdateData(no);
	}
	
	
	
	//@Update("UPDATE springBoard SET name=#{name},subject=#{subject},content=#{content} WHERE no=#{no}")
	public boolean boardUpdate(BoardVO vo)
	{
		boolean bcheck=false;
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			bcheck=true;
			mapper.boardUpdate(vo);
		}
		return bcheck;
	}
	
	//@Delete("DELETE FROM springBoard WHERE no=#{no}")
	public boolean boardDelete(int no,String pwd)
	{
		boolean bcheck=false;
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd))
		{
			bcheck=true;
			mapper.boardDelete(no);
		}
		return bcheck;
	}
	
}
