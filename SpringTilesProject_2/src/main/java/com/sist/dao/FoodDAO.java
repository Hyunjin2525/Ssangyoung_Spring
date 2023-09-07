package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	//@Select("SELECT cno,title,poster FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> fooCategoryData()
	{
		return mapper.fooCategoryData();
	}
	

	public List<FoodVO> foodListData(int cno)
	{
		return mapper.foodListData(cno); 
	}
	
	public CategoryVO foodcategoryInfoData(int cno)
	{
		return mapper.foodcategoryInfoData(cno);
	}
	
	public List<FoodVO> foodTop7()
	{
		return mapper.foodTop7();
	}

	
	
	public List<FoodVO> foodFindData(Map map)
	{
		return mapper.foodFindData(map);
	}
	
	public int foodFindTotalPage(String fd)
	{
		return mapper.foodFindTotalPage(fd);
	}
	
	
}
