package com.sist.exception;

import java.sql.SQLException;
import java.io.*;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonsException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex)
	{
		System.out.println("========= RuntimeException 발생 ==========");
		ex.printStackTrace();
		System.out.println("============================================");
	}
	//IOException , SqlException, Exception
	
	@ExceptionHandler(IOException.class)
	public void IOException(IOException ex)
	{
		System.out.println("========= RuntimeException 발생 ==========");
		ex.printStackTrace();
		System.out.println("============================================");
	}
	
	@ExceptionHandler(SQLException.class)
	public void SqlException(SQLException ex)
	{
		System.out.println("========= RuntimeException 발생 ==========");
		ex.printStackTrace();
		System.out.println("============================================");
	}
	
	@ExceptionHandler(Exception.class)
	public void Exception(Exception ex)
	{
		System.out.println("========= RuntimeException 발생 ==========");
		ex.printStackTrace();
		System.out.println("============================================");
	}
}

