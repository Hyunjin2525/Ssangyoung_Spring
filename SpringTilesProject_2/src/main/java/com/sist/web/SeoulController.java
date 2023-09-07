package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class SeoulController {

	@Autowired
	private SeoulDAO dao;
	private String[] names= {"","서울 명소","서울 자연","서울 쇼핑"};
	@GetMapping("seoul/list.do")
	public String seoul_list(String page,String no, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowsize=20;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		
		if(no==null)
			no="1";
		int index=Integer.parseInt(no); // 1=location,2=nature,3=shop
		
		map.put("pNo", index);
		map.put("pStart", start);
		map.put("pEnd", end);
		
		List<SeoulVO> list=dao.seoulListData(map);
		
		map=new HashMap();
		map.put("pNo", index);
		int totalpage=dao.seoulTotalPage(map);
		
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
		model.addAttribute("no", index);
		model.addAttribute("name", names[index]);
		return "seoul/list";
	}
	@GetMapping("seoul/detail.do")
	public String seoul_detail(int type,int no,Model model)
	{
		String[] tables={"","seoul_location","seoul_nature","seoul_shop"};
		
		Map map=new HashMap();
		map.put("no", no);
		map.put("table_name", tables[type]);
		
		SeoulVO vo=dao.seoulDetailData(map);
		
		model.addAttribute("vo",vo);
		model.addAttribute("no",type);
		model.addAttribute("tname",names[type]);
		return "seoul/detail";
	}
}
