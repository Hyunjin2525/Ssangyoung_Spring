package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<CategoryVO> foodCategoryData(Map map)
	{
		return mapper.foodCategoryData(map);
	}
	
	//@Select("SELECT title,subject FROM food_category WHERE cno=#{cno}")
	public CategoryVO foodCategoryInfoData(int cno)
	{
		return mapper.foodCategoryInfoData(cno);
	}
	
	
	
	//@Select("SELECT fno,cno,name,phone,type,parking,price,time,menu,score "
			//+ "FROM food_house WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno)
	{
		return mapper.foodListData(cno);
	}
	
	//@Select("SELECT fno,cno,name,score,poster,phone,address,type,time,parking,price,menu FROM food_house WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
}
