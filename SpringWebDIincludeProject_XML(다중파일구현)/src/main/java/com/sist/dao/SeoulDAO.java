package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.SeoulMapper;
import com.sist.vo.SeoulVO;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	
	public List<SeoulVO> seoulLocationListData(Map map)
	{
		return mapper.seoulLocationListData(map);
	}
	

	public int seoulLocationTotalPage()
	{
		return mapper.seoulLocationTotalPage();
	}
	

	public List<SeoulVO> seoulNatureList(Map map)
	{
		return mapper.seoulNatureList(map);
	}
	

	public List<SeoulVO> seoulShopList(Map map)
	{
		return mapper.seoulShopList(map);
	}
	
	public int seoulNatureTotalPage()
	{
		return mapper.seoulNatureTotalPage();
				
	}
	
	public int seoulShopTotalPage()
	{
		return mapper.seoulShopTotalPage();
	}
}
