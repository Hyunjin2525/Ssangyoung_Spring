package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.DataBoardMapper;
import com.sist.vo.DataBoardVo;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	

	public List<DataBoardVo> databoardListData(Map map)
	{
		return mapper.databoardListData(map);
	}

	public int databoardTotalpage()
	{
		return mapper.databoardTotalpage();
	}
	

	public void databoardInsert(DataBoardVo vo)
	{
		mapper.databoardInsert(vo);
	}
	
	
	public DataBoardVo databoardDetailData(int no)
	{
		mapper.dataBoardHit(no);
		return mapper.databoardDetailData(no);
	}
	
	public List<DataBoardVo> databoardFindData(Map map)
	{
		return mapper.databoardFindData(map);
	}
	
	public DataBoardVo databoardUpdateData(int no)
	{
		return mapper.databoardDetailData(no);
	}
	
	public boolean databoardUpdate(DataBoardVo vo)
	{
		boolean bcheck=false;
		String db_pwd=mapper.databoardPWD(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			bcheck=true;
			mapper.databoardUpdate(vo);
		}
		return bcheck;
	}
	
	public boolean databoardDelete(int no,String pwd)
	{
		boolean bcheck=false;
		String db_pwd=mapper.databoardPWD(no);
		if(db_pwd.equals(pwd))
		{
			bcheck=true;
			mapper.databoardDelete(no);
		}
		return bcheck;
	}
}
