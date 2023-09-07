package com.sist.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonsRestException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex)
	{
		System.out.println("========= RuntimeException 발생 ==========");
		ex.printStackTrace();
		System.out.println("============================================");
	}
	
	@ExceptionHandler(IOException.class)
	public void IOException(IOException ex)
	{
		System.out.println("========= IOException 발생 ==========");
		ex.printStackTrace();
		System.out.println("============================================");
	}
	
	@ExceptionHandler(SQLException.class)
	public void SqlException(SQLException ex)
	{
		System.out.println("========= SqlException 발생 ==========");
		ex.printStackTrace();
		System.out.println("============================================");
	}
	
	@ExceptionHandler(Exception.class)
	public void Exception(Exception ex)
	{
		System.out.println("========= Exception 발생 ==========");
		ex.printStackTrace();
		System.out.println("============================================");
	}
}
