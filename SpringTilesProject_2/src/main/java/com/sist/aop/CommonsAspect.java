package com.sist.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessorOrder;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.dao.FoodDAO;
import com.sist.dao.SeoulDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.SeoulVO;

@Aspect
@Component
public class CommonsAspect {
	@Autowired
	private FoodDAO fdao;
	@Autowired
	private SeoulDAO sdao;
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void footerData()
	{
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<SeoulVO> seoulList=sdao.seoulTop7();
		List<FoodVO> foodList=fdao.foodTop7();
		
		request.setAttribute("seoulList", seoulList);
		request.setAttribute("foodList", foodList);
	}
}
