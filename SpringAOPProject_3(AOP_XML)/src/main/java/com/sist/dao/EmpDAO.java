package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

public class EmpDAO {
	private EmpMapper mapper;
	
	public void setMapper(EmpMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<EmpVO> empListData()
	{
		return mapper.empListData();
	}
	
	public EmpVO empDetailData(int empno)
	{
		return mapper.empDetailData(empno);
	}
	
	public List<EmpVO> empFindData(String ename)
	{
		return mapper.empFindData(ename);
	}
	
}
