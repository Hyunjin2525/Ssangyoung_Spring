package com.sist.web.controller;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.GroundDAO;
import com.sist.web.entity.GroundVO;

@Controller
@RequestMapping("ground/")
public class FootballController {
	@Autowired
	private GroundDAO dao;
	
	@RequestMapping("list")
	public String good_list(Model model,String page,String fd)
	{
		if(fd==null)
			fd="서울";
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int start=(9*curpage)-9;
		List<GroundVO> list=dao.ground_list_find(fd, start);
		
		for(GroundVO vo:list)
		{
			String img=vo.getGimage();
			if(img.contains("^"))
			{
				img=img.substring(0,img.indexOf("^"));
				vo.setGimage(img);
			}
			
		}
		
		
		int totalpage=dao.ground_total_page(fd);
		
		final int BLOCK=5;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		model.addAttribute("fd",fd);
		
		model.addAttribute("main_html","ground/list");
		return "main";
	}
	
	@GetMapping("detail")
	public String ground_detail(Model model,int gno)
	{
		GroundVO vo=dao.findByGno(gno);
		String img=vo.getGimage();
		if(img.contains("^"))
		{
			img=img.substring(0,img.indexOf("^"));
			vo.setGimage(img);
		}
		model.addAttribute("vo",vo);
		model.addAttribute("main_html","ground/detail");
		return "main";
	}
	
	@GetMapping("weather")
	public String seoul_weather(Model model)
	{
		  String html="";
		 try
		  {
			  Document doc=Jsoup.connect("https://korean.visitseoul.net/weather").get();
			  Element section=doc.selectFirst("section#content");
			  html="<section id=\"content\">";
			  html+=section.html();
			  html+="</section>";
			  //<img src="https://korean.visitseoul.net/resources/theme/images/weather/img-weather10.png" alt="흐리고 비">
			  html=html.replace("src=\"","src=\"https://korean.visitseoul.net" );
			  html=html.replace("제공 : 케이웨더(Kweather)","" );
		  }catch(Exception ex) {}
		model.addAttribute("main_html","ground/weather");
		model.addAttribute("html",html);
		return "main";
	}
}
