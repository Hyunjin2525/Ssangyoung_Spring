package com.sist.commons;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//모든 Controller의 예외처리 => 전역
@ControllerAdvice //예외처리
public class CommonException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex)
	{
		System.out.println("======= RuntimeException =======");
		ex.printStackTrace();
		System.out.println("=================================");
	}
	
	@ExceptionHandler(SQLException.class)
	public void SQLException(SQLException ex)
	{
		System.out.println("======= SQLException =======");
		ex.printStackTrace();
		System.out.println("=================================");
	}
	
	@ExceptionHandler(IOException.class)
	public void IOException(IOException ex)
	{
		System.out.println("======= IOException =======");
		ex.printStackTrace();
		System.out.println("=================================");
	}
	
	@ExceptionHandler(Exception.class)
	public void Exception(Exception ex)
	{
		System.out.println("======= Exception =======");
		ex.printStackTrace();
		System.out.println("=================================");
	}
}
