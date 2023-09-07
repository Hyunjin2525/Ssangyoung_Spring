package com.sist.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.FoodDAO;
//import com.sist.web.service.FoodService;
//import com.sist.web.vo.FoodVO;
import com.sist.web.entity.*;
@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	
	//@Autowired
	//private FoodService service;
	
	@RequestMapping("food/find")
	public String food_find(String page,String fd,Model model)
	{
		if(fd==null)
		{
			fd="마포";
		}
		
		if(page==null)
		{
			page="1";
		}
		
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		
		int start=(12*curpage)-12;
		int end=12*curpage;
		map.put("start", start);
		map.put("address", fd);
		
		//List<FoodVO> list=service.foodFindData(map);
		//int totalpage=service.foodFindTotalpage(fd);
		
		List<FoodEntity> list=dao.foodFindData(fd, start);
		int totalpage=dao.foodFindTotalPage(fd);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		int endpage=((curpage-1)/BLOCK*BLOCK)+1;
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		
		return "food/find";
	}
	
	@GetMapping("food/detail")
	public String food_detail(int fno,Model model)
	{
		FoodEntity vo=dao.findByFno(fno);
		String poster=vo.getPoster();
		String[] temp=poster.split("\\^");
		List<String> pList=Arrays.asList(temp);
		
		model.addAttribute("plist",pList);
		model.addAttribute("vo",vo);
		
		
		return "food/detail";
	}
}
