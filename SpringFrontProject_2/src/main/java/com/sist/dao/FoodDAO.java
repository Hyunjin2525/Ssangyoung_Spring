package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodLocationVO;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	

	public List<FoodLocationVO> foodListData(Map map)
	{
		return mapper.foodListData(map);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
	
	//상세보기
	public FoodLocationVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
	//검색 ==> Vue/React => 실시간
	public List<FoodLocationVO> foodFindData(Map map)
	{
		return mapper.foodFindData(map);
	}
	
	public int foodFind(String address)
	{
		return mapper.foodFindTotal(address);
	}
}
