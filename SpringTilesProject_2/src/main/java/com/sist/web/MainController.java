package com.sist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class MainController {
	@Autowired
	private FoodDAO dao;
	@GetMapping("main/main.do")
	public String main_main(Model model)
	{
		List<CategoryVO> clist=dao.fooCategoryData();
		model.addAttribute("clist",clist);
		return "main";
	}
}
