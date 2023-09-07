package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.dao.SeoulLocationDAO;
import com.sist.web.entity.FoodEntity;
import com.sist.web.entity.SeoulLocationEntity;

import java.util.*;
@Controller
public class SeoulController {
	@Autowired
	private SeoulLocationDAO dao;
	
	@GetMapping(value = "seoul/list")
	public String seoulList(String page,Model model)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int totalpage=dao.seoulTotalpage();
		
		int start=(12*curpage)-12;
		
		
		List<SeoulLocationEntity> list=dao.seoulListData(start);

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
		
		return "seoul/list";
		
				
	}
	

	
}
