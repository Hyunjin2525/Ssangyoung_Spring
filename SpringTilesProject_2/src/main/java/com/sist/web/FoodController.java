package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	//../food/food_list.do?cno=${vo.cno }
	
	@GetMapping("food/food_list.do")
	public String food_list(int cno,Model model)
	{
		List<FoodVO> list=dao.foodListData(cno);
		for(FoodVO vo:list)
		{
			String address=vo.getAddress();
			address=address.substring(0,address.lastIndexOf("지번"));
			vo.setAddress(address);
			
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
		}
		CategoryVO vo=dao.foodcategoryInfoData(cno);
		model.addAttribute("cvo",vo);
		model.addAttribute("list",list);
		return "food/food_list";
	}
	
	@GetMapping("food/food_find.do")
	public String food_find(String fd, String page, Model model)
	{
		if(fd==null)
			fd="마포";
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		Map map=new HashMap();
		int rowsize=20;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		map.put("start", start);
		map.put("end", end);
		map.put("fd", fd);
		
		List<FoodVO> list=dao.foodFindData(map);
		for(FoodVO vo:list)
		{
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
		}
		int totalpage=dao.foodFindTotalPage(fd);
		
		final int BlOCK=5;
		int startpage=((curpage-1)/BlOCK*BlOCK)+1;
		int endpage=((curpage-1)/BlOCK*BlOCK)+BlOCK;
		
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		model.addAttribute("fd",fd);
		return "food/food_find";
	}
	
}
