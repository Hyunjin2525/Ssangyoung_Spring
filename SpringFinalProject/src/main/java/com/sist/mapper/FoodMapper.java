package com.sist.mapper;

import java.util.*;
import org.apache.ibatis.annotations.Select;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
import com.sist.vo.ReplyVO;

public interface FoodMapper {

	@Select("SELECT cno,title,poster,subject "
			+ "FROM food_category "
			+ "ORDER BY cno ASC")
	public List<CategoryVO> foodCategoryListData();
	
	@Select("SELECT title,subject FROM food_category "
			+ "WHERE cno=#{cno}")
	public CategoryVO foodCategoryInfoData(int cno);
	
	//<select id="foodFindData" resultType="FoodVO" parameterType="hashmap">
	public List<FoodVO> foodFindData(Map map);
	
	//<select id="foodFindTotalPage" resultType="int" parameterType="hashmap">
	public int foodFindTotalPage(Map map);
	
	@Select("SELECT fno,name,tel as phone,poster,address,type,time,parking,menu,price,score FROM food_location WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Select("SELECT fno,name,address,phone,type,poster,score FROM food_house WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	@Select("SELECT fno,cno,name,phone,poster,address,type,time,parking,price,score FROM food_house WHERE fno=#{fno}")
	public FoodVO foodDetailHouseData(int fno);
	
	// 추천
	@Select("SELECT DISTINCT name FROM food_location WHERE LENGTH(name)>1")
	public List<String> foodNameGetData();
	
	// 실제 정보
	@Select("SELECT fno,name,poster,rownum FROM food_location "
			+ "WHERE name=#{name} AND rownum<=1")//rownum을 사용해서 데이터 하나만 추출
	public FoodVO foodRecommandInfoData(String name);
	
}
