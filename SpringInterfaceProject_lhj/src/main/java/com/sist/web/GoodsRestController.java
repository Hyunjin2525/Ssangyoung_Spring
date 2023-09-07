package com.sist.web;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@RestController
public class GoodsRestController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping(value = "goods/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String goodsListData(String page)
	{
		String result="";
		
		try {
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			
			int rowsize=12;
			int start=(rowsize*curpage)-(rowsize-1);
			int end=rowsize*curpage;
			
			Map map=new HashMap();
			map.put("start", start);
			map.put("end", end);
			
			
			List<GoodsVO> list=dao.goodsListData(map);
			
			int totalPage=dao.footballTotalPage();
			JSONArray arr=new JSONArray();
			int i=0;
			for(GoodsVO vo:list)
			{
				JSONObject obj=new JSONObject();
				obj.put("gno", vo.getGno());
				obj.put("goods_name",vo.getGoods_name());
				obj.put("goods_image", vo.getGoods_image());
				if(i==0)
				{
					obj.put("curpage", curpage);
					obj.put("totalpage", totalPage);
				}
				arr.add(obj); 
				i++;
			}
			result=arr.toJSONString();
			
		} catch (Exception e) {}
		
		return result;
	}
	
	@GetMapping(value = "goods/find_vue.do",produces = "text/plain;charset=UTF-8")
	public String goodsFindData(String gname,int page)
	{
		String result="";
		
		try {
		
			int curpage=page;
			int start=(12*curpage)-11;
			int end=12*curpage;
			
	
			
			Map map=new HashMap();
			map.put("start", start);
			map.put("end", end);
			map.put("gname", gname);
			
			List<GoodsVO> list=dao.goodsFindData(map);
			int totalpage=dao.goodsFindTotalPage(gname);
					
			
			JSONArray arr=new JSONArray();
			int i=0;
			for(GoodsVO vo:list)
			{
				JSONObject obj=new JSONObject();
				obj.put("gno", vo.getGno());
				obj.put("goods_name",vo.getGoods_name());
				obj.put("goods_image", vo.getGoods_image());
				if(i==0)
				{
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
				}
				arr.add(obj); 
				i++;
			}
			result=arr.toJSONString();
			
		} catch (Exception e) {}
		
		return result;
	}
}
