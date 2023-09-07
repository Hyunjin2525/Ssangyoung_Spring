package com.sist.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		dao.empListData();
		//dao.empDetailData(7788);
		dao.empFindData("A");
		/*
		 * public void afterReturning(Object obj) throws Throwable
	{
		System.out.println("afterReturning Call.. 정상수행");
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob()+" "+vo.getDbday()+" "+vo.getSal());
		}
	}
	
		 * 
		 */
	}
}	
