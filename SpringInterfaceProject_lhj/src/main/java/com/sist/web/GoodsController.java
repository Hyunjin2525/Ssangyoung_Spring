package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;


@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("goods/list.do")
	public String goodsListData()
	{
		return "goods/list";
	}
	
	@GetMapping("goods/find.do")
	public String goodsFindData()
	{
		return "goods/find";
	}
}
