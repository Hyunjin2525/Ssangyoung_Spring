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

import com.sist.web.dao.SeoulLocationDAO;
import com.sist.web.entity.SeoulEntity;

@Controller
@RequestMapping("seoul/")
public class SeoulController {
	@Autowired
	private SeoulLocationDAO dao;
	
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
		model.addAttribute("main_html","seoul/weather");
		model.addAttribute("html",html);
		return "main";
	}
	@GetMapping("location")
	public String seoul_location(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int start=(20*curpage)-20;
		List<SeoulEntity> list=dao.seoulLocationListData(start);
		
		int totalpage=dao.seoulLocationtotal();
				
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		model.addAttribute("main_html","seoul/location");
		return "main";
	}
	
	@GetMapping("nature")
	public String seoul_nature(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int start=(20*curpage)-20;
		List<SeoulEntity> list=dao.seoulNatureListData(start);
		
		int totalpage=dao.seoulNaturetotal();
				
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		model.addAttribute("main_html","seoul/nature");
		return "main";
	}
	
	@GetMapping("shop")
	public String seoul_shop(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int start=(20*curpage)-20;
		List<SeoulEntity> list=dao.seoulShopListData(start);
		
		int totalpage=dao.seoulShoptotal();
				
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		model.addAttribute("main_html","seoul/shop");
		return "main";
	}
}
