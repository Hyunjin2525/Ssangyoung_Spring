package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RecipeVO;

public interface RecipeMapper {
	@Select("SELECT no,title,poster,num FROM (SELECT no,title,poster,rownum as num "
			+ "FROM (SELECT no,title,poster FROM recipe ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)) FROM recipe")
	public int recipeTotalPage();
	
}
