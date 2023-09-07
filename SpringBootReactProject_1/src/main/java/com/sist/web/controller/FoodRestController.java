package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.FoodCategoryDAO;
import com.sist.web.dao.FoodDAO;
import com.sist.web.dao.FoodLocationDAO;
import com.sist.web.entity.CategoryEntity;
import com.sist.web.entity.FoodEntity;
import com.sist.web.entity.FoodLocationEntity;
import com.sist.web.entity.PageVO;

@RestController
@RequestMapping("food/")
@CrossOrigin("http://localhost:3000")//3000번 포트를 허용한다는 뜻(react 연결할 때 꼭 사용)
public class FoodRestController {
	@Autowired
	private FoodCategoryDAO dao;
	@Autowired
	private FoodLocationDAO ldao;
	@Autowired
	private FoodDAO fdao;
	
	@GetMapping("category1")
	public List<CategoryEntity> food_category1()
	{
		List<CategoryEntity> list=dao.categoryData1();
		
		return list; //react에서 자동으로 json으로 변경
	}
	@GetMapping("category2")
	public List<CategoryEntity> food_category2()
	{
		List<CategoryEntity> list=dao.categoryData2();
		
		return list; //react에서 자동으로 json으로 변경
	}
	@GetMapping("category3")
	public List<CategoryEntity> food_category3()
	{
		List<CategoryEntity> list=dao.categoryData3();
		
		return list; //react에서 자동으로 json으로 변경
	}
	
	@GetMapping("category_info_react")
	public CategoryEntity category_info(int cno)
	{
		CategoryEntity vo=dao.findByCno(cno);
		return vo;
	}
	@GetMapping("food_list_react")
	public List<FoodEntity> food_list(int cno)
	{
		List<FoodEntity> list=fdao.food_list_data(cno);
		return list;
	}
	@GetMapping("food_detail_react")
	public FoodEntity food_detail(int fno)
	{
		FoodEntity vo=fdao.findByFno(fno);
		return vo;
	}
	
	@RequestMapping("food_find_react")
	public List<FoodLocationEntity> food_find(String address,int page)
	{
		if(address==null)
		{
			address="마포";
		}
		
		int curpage=page;
		int start=(20*curpage)-20;
		List<FoodLocationEntity> list=ldao.foodFindData(address, start);
		return list;
	}
	@GetMapping("food_page_react")
	public PageVO food_page(String address,int page)
	{
		int totalpage=ldao.foodFindTotal(address);
		final int BLOCK=5;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;	
		PageVO vo=new PageVO();
		vo.setCurpage(page);
		vo.setEndpage(endpage);
		vo.setStartpage(startpage);
		vo.setTotalpage(totalpage);
		
		return vo;
	}
}

