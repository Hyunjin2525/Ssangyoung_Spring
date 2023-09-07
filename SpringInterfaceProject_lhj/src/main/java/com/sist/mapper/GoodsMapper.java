package com.sist.mapper;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.GoodsVO;

public interface GoodsMapper {
	@Select("SELECT gno, goods_name, goods_image, TO_CHAR(cprice, '999,999') as dbcprice, TO_CHAR(price, '999,999') as dbprice, num "
	         + "FROM (SELECT gno, goods_name, goods_image, cprice, price, rownum AS num "
	         + "FROM (SELECT gno, goods_name, goods_image, cprice, price " 
	         + "FROM goods ORDER BY gno ASC)) WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods")
	public int goodsTotalPage();
	
	@Select("SELECT gno,goods_name,goods_image,num FROM (SELECT gno,goods_name,goods_image,rownum as num FROM (SELECT "
			+ "gno,goods_name,goods_image FROM gooods WHERE goods_name LIKE '%'||#{gname}||'%' ORDER BY fno ASC)) WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods WHERE goods_name LIKE '%'||#{gname}||'%'")
	public int goodsFindTotalPage(String gname);
}
