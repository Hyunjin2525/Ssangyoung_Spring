package com.sist.aop;

import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.dao.AspectDAO;
import com.sist.vo.FoodVO;


@Aspect	// 공통 모듈
@Component	// 메모리 할당
public class FooterAspect {
	@Autowired
	private AspectDAO dao;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void footerData()
	{
		//=> 매개변수에 값을 채워준다 (DispatcherServlet)
		//=> @Controller,@RestController에서만 가능
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<FoodVO> flist=dao.foodTop7Data();
		List<String> slist=dao.seoulTop7Data();
		List<String> rlist=dao.recipeTop7Data();
		
		request.setAttribute("flist", flist);
		request.setAttribute("slist", slist);
		request.setAttribute("rlist", rlist);
				
				
	}
	
}
