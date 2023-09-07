package com.sist.main;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.*;

import javassist.convert.TransformNewClass;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * public void locationMain() { ApplicationContext app=new
	 * ClassPathXmlApplicationContext("app.xml"); LocationDAO
	 * dao=(LocationDAO)app.getBean("ldao"); List<SeoulLocationVO>
	 * list=dao.locationListData(); for(SeoulLocationVO vo:list) {
	 * System.out.println(vo.getNo()+"."+vo.getTitle()); } }
	 * 
	 * @Test public void locationDetail() { Scanner scan=new Scanner(System.in);
	 * System.out.println("명소 번호:"); int no=scan.nextInt();
	 * 
	 * ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
	 * LocationDAO dao=(LocationDAO)app.getBean("ldao"); SeoulLocationVO
	 * vo=dao.locationDetailData(no); System.out.println("TITLE:"+vo.getTitle());
	 * System.out.println("Address:"+vo.getAddress());
	 * System.out.println("Message:"+vo.getTitle()); }
	 */
	@Test
	public void natureMain()
	{
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		NatureDAO dao=(NatureDAO)app.getBean("ndao");
		List<SeoulNatureVO> list=dao.natureListData();
		for(SeoulNatureVO vo:list)
		{
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
	}
	@Test
	public void natureDetail()
	{
		Scanner scan=new Scanner(System.in);
		System.out.println("번호:");
		int no=scan.nextInt();
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		NatureDAO dao=(NatureDAO)app.getBean("ndao");
		SeoulNatureVO vo=dao.natureDetailData(no);
		System.out.println("title:"+vo.getTitle());
	}

}
