package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.RecipeDAO;
import com.sist.vo.RecipeVO;

@RestController
public class RecipeRestController {
	@Autowired
	private RecipeDAO dao;
	
	@GetMapping(value = "recipe/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String recipeListVue(int page)
	{
		String result="";
		try {
			int curpage=page;
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			
			List<RecipeVO> list=dao.recipeListData(map);
			
			int totalpage=dao.recipeTotalPage();
			final int BLOCK=5;
			int startpage=((curpage-1)/BLOCK*BLOCK)+1;
			int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endpage>totalpage)
				endpage=totalpage;
			
			JSONArray arr=new JSONArray();
			int i=0;
			for(RecipeVO vo:list)
			{
				JSONObject obj=new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("title", vo.getTitle());
				obj.put("poster", vo.getPoster());
				if(i==0)
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
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
