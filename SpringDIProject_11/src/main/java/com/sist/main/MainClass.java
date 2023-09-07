package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.dao.*;

import sun.print.resources.serviceui;
@Component
public class MainClass {
	@Autowired
	private FoodService service;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		Scanner scan=new Scanner(System.in);
		
		MainClass mc=(MainClass)app.getBean("mainClass");//default 이름은 첫글자가 소문자
		
		List<CategoryVO> list=mc.service.cateListData();
		for(CategoryVO vo:list)
		{
			System.out.println(vo.getCno()+"."+vo.getTitle());
		}
		System.out.println("=============================================");
		System.out.print("카테고리 번호 선택:");
		int cno=scan.nextInt();
		List<FoodVO> flist=mc.service.foodCategoryListData(cno);
		for(FoodVO vo:flist)
		{
			System.out.println(vo.getFno()+"."+vo.getName());
		}
		System.out.println("==============================================");
		System.out.println("맛집 번호 선택:");
		int fno=scan.nextInt();
		FoodVO vo=mc.service.fooDetailData(fno);
		System.out.println("맛집명:"+vo.getName()+"("+vo.getScore()+")");
		System.out.println("주소:"+vo.getAddress());
		System.out.println("전화번호:"+vo.getPhone());
		System.out.println("음식 종류:"+vo.getType());
		System.out.println("가격대:"+vo.getPrice());
		System.out.println("영업시간:"+vo.getTime());
		System.out.println("메뉴:"+vo.getMenu());
	}

}
