package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.RowSetInternal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.SeoulDAO;
import com.sist.vo.SeoulVO;


@Controller
@RequestMapping("seoul/")
public class SeoulController {

	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("location.do")
	public String seoulLocation(String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=12;
		int start= (rowsize*curpage)-(rowsize-1);
		int end= rowsize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<SeoulVO> list=dao.seoulLocationListData(map);
		final int BLOCK=5;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=dao.seoulLocationTotalPage();
		
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		
		model.addAttribute("main_jsp", "../seoul/location.jsp");
		return "main/main";
	}
	@GetMapping("nature.do")
	public String seoulNature(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=12;
		int start= (rowsize*curpage)-(rowsize-1);
		int end= rowsize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<SeoulVO> list=dao.seoulNatureList(map);
		
		final int BLOCK=5;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=dao.seoulNatureTotalPage();
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		
		model.addAttribute("main_jsp", "../seoul/nature.jsp");
		
		return "main/main";
	}
	
	@GetMapping("shop.do")
	public String seoulShop(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=12;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<SeoulVO> list=dao.seoulShopList(map);
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=dao.seoulShopTotalPage();
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("startpage",startPage);
		model.addAttribute("endpage",endPage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		
		
		model.addAttribute("main_jsp", "../seoul/shop.jsp");
		return "main/main";
	}
}
