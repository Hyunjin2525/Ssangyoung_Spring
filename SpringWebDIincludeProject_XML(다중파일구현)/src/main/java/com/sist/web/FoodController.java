package com.sist.web;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/food_list.do")
	public String food_list(int cno,Model model)
	{
		CategoryVO vo=dao.foodCategoryInfoData(cno);
		List<FoodVO> list=dao.foodListData(cno);
		
		model.addAttribute("list", list);
		model.addAttribute("cvo",vo);
		model.addAttribute("main_jsp","../food/food_list.jsp");
		return "main/main";
	}
	
	@GetMapping("food/food_detail.do")
	public String food_detail(int fno,Model model)
	{
		FoodVO vo=dao.foodDetailData(fno);
		
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp","../food/food_detail.jsp");
		return "main/main";
	}
	
	@GetMapping("food/food_before_detail.do")
	public String food_before_detail(int fno,RedirectAttributes ra,HttpServletResponse response)
	{
		/*
		 *  Model : forward일때 값을 전송
		 *  RedirectAttributes : sendRedirect일 때 값을 전송
		 */
		Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno));
		//cookie는 저장이 String만 가능
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		//브라우저로 전송
		response.addCookie(cookie);
		
		ra.addAttribute("fno",fno); //?fno= 대신 이렇게도 값을 보낼 수 있음
	
		return "redirect:../food/food_detail.do";
	}
	
}
