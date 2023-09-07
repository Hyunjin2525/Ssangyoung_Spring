package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodLocationVO;

import oracle.net.aso.b;

import java.util.*;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list_vue(String page)
	{
		String result="";
		try {
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end",curpage*12);
			List<FoodLocationVO> list=dao.foodListData(map);
			int totalPage=dao.foodTotalPage();
			
			
			// List => [] =>JSONArray
			//FoodLocationVo => JSONObject {} 로 만들어줘야 자바스크립트가 인식
			
			JSONArray arr=new JSONArray();
			int i=0;
			for(FoodLocationVO vo:list)
			{
				JSONObject obj=new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("score", vo.getScore());
				String poster=vo.getPoster();
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				obj.put("poster", poster);
				if(i==0)
				{
					obj.put("curpage", curpage);
					obj.put("totalpage", totalPage);
				}
				arr.add(obj); //배열에 첨부 [{},{},{}]
				i++;
			}
			result=arr.toJSONString();
		} catch (Exception e) {}
		return result;
	}
	
	/*
	 * 
	 *  1.일반문자열 => NOID,NOPWD,OK => text/html
	 *  2.데이터묶음(JSON) => text/plain
	 *  	List/VO
	 *  		 --- {} => JSONObject
	 *  	----[] => JSONArray
	 *  	--------------------------> jackson (Spring-Boot)
	 *  3.XML전송 => text/html
	 */
	@GetMapping(value = "food/find_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_find_vue(int page,String fd)
	{
		String result="";
		try {
			int curpage=page;
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end",curpage*12);
			map.put("address", fd);
			List<FoodLocationVO> list=dao.foodFindData(map);
			int totalpage=dao.foodFind(fd);
			final int BLOCK=5;
			int startpage=((curpage-1)/BLOCK*BLOCK)+1;
			int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endpage>totalpage)
				endpage=totalpage;
			//JSON
			JSONArray arr=new JSONArray(); //List 대신
			int i=0;
			for(FoodLocationVO vo:list)
			{
				JSONObject obj=new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("score", vo.getScore());
				String poster=vo.getPoster();
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				obj.put("poster", poster);
				if(i==0) //페이지는 하나만 필요하기 때문에 0번째 배열에만 값을 넣어준다(다른 데이터들은 여러개 필요함=VO)
				{
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
					obj.put("startpage", startpage);
					obj.put("endpage", endpage);
					
				}
				arr.add(obj);
				i++;
			
			}
			result=arr.toJSONString();
		} catch (Exception e) {}
		
		return result;
	}
}
