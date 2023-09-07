package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
import com.sist.vo.SeoulVO;

@Service
public class FoodServiceImpl implements FoodService{

	@Autowired
	private FoodDAO dao;
	@Override
	public List<CategoryVO> fooCategoryData() {
		return dao.fooCategoryData();
	}

	@Override
	public List<FoodVO> foodTop7() {
		// TODO Auto-generated method stub
		return dao.foodTop7();
	}

	@Override
	public List<SeoulVO> seoulTop7() {
		// TODO Auto-generated method stub
		return dao.seoulTop7();
	}

}
