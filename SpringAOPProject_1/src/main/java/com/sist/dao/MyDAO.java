package com.sist.dao;

public class MyDAO {
	public void getConnection()
	{
		System.out.println("오라클 연결");
	}
	public void disConnection()
	{
		System.out.println("오라클 해제");
	}
	public void aopSelect()
	{
		getConnection();
		System.out.println("SELECT문장 실행..");
		disConnection();
	}
	public void aopInsert()
	{
		getConnection();
		System.out.println("Insert문장 실행..");
		disConnection();
	}
	public void aopUpdate()
	{
		getConnection();
		System.out.println("Update문장 실행..");
		disConnection();
	}
	public void aopDelete()
	{
		getConnection();
		System.out.println("Delete문장 실행..");
		disConnection();
	}
}
