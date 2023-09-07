package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.FoodDAO;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/food_category_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_category_vue(int cno)
	{
		String json="";
		try {
			Map map=new HashMap();
			map.put("cno", cno);
			List<CategoryVO> list=dao.foodCategoryListData(map);
			
			ObjectMapper mapper=new ObjectMapper();
			json=mapper.writeValueAsString(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return json;
	}
	
	@GetMapping(value = "food/food_category_info_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_category_info(int cno) throws Exception
	{
		CategoryVO vo=dao.foodCategoryInfoData(cno);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
		return json;
	}
	
	@GetMapping(value = "food/food_food_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list_vue(int cno) throws Exception
	{
		List<FoodVO> list=dao.foodListData(cno);
		
		for(FoodVO vo:list)
		{
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
			String address=vo.getAddress();
			address=address.substring(0,address.indexOf("지번"));
			vo.setAddress(address);
		}
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@GetMapping(value = "food/food_detail_vue",produces = "text/plain;charset=UTF-8")
	public String food_detail_vue(int fno,HttpSession session) throws Exception
	{
		String id=(String)session.getAttribute("id");
		System.out.println(id);
		String result="";
		FoodVO vo=dao.foodDetailData(fno);
		String addr=vo.getAddress();
		addr=addr.substring(0,addr.indexOf("지번"));
		vo.setAddress(addr.trim());
		vo.setSessionId(id); //세션첨부
		ObjectMapper mapper=new ObjectMapper();
		result=mapper.writeValueAsString(vo);
		
		
		
		return result;
	}
}
