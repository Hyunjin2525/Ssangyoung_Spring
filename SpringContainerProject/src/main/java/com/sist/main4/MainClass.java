package com.sist.main4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 *  컨테이너 (스프링이 주로 하는 역할)
 *  	=> 개발자가 만드는 클래스 (한개의 기능을 수행: 컴포넌트)
 *  		컴포넌트 여러개를 관히하는 역할 (컨테이너)
 *  		JButtin, JTextField... (컴포넌트)
 *  ------------------------------------------------ JFraime( 컨테이너)
 *  컨테이너의 역할
 *  	1) 객체 생성 ==> new (##new사용 => VO: 사용자 정의 데이터형)
 *  	2) 객체의 변수 초기화 ==> DI
 *  	3) 객체의 소멸하는 역할
 *  	---------------------------------> 객체
 *  	=> 핵싱 기능에만 집중해서 개발
 *  
 *  	스프링에서 지원하는 컨테이너
 *  	-----------------------
 *  	BeanFactory
 *  		|
 *  	ApplicationCOntext (Application) ====> core(DI), AOP
 *  		|	- AnnotationConfigApplicationContext
 *  			------------------------------------
 *  				Spring 5의 핵심 (XML을 사용하지않고 순수하게 자바)
 *  	WebApplicationContext (MVC) ====> core(DI), AOP, MVC
 * 
 */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		//ClassPathXmlApplicationContext : XML을 파싱 (XML에 등록된 클래스를 읽어간다)
		//classPath =: src/main/java
		A a=(A)app.getBean("a");
		a.display();
	}

}
