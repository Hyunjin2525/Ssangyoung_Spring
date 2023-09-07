package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.sist.vo.*;
public interface SeoulMapper {
	
	@Select("SELECT no,title,poster,rownum FROM (SELECT no,title,poster FROM seoul_location ORDER BY no ASC) "
			+ "WHERE rownum<=7")
	public List<SeoulVO> seoulTop7();
	
	@Select(value = "{CALL seoulListData(#{pNo,mode=IN,javaType=java.lang.Integer},#{pStart,mode=IN,javaType=java.lang.Integer},"
			+ "#{pEnd,mode=IN,javaType=java.lang.Integer},#{pResult,mode=OUT,jdbcType=CURSOR,resultMap=seoulMap})}")
	@Options(statementType = StatementType.CALLABLE)
	public List<SeoulVO> seoulListData(Map map);
	
	@Select(value = "{CALL seoulTotalPage(#{pNo,mode=IN,javaType=java.lang.Integer},#{pTotal,mode=OUT,jdbcType=INTEGER})") // out타입은 JDBC로 받는다
	@Options(statementType = StatementType.CALLABLE)
	public Integer seoulTotalPage(Map map);
	
	/*
	 * 	<select id="seoulDetailData" resultType="com.sist.vo.SeoulVO" parameterType="hashmap">
 		SELECT no,title,poster,msg,address
 		FROM ${table_name}
 		WHERE no=#{no}
 		</select>
	 * 
	 */
	// resultType	id             parameterType
	public SeoulVO seoulDetailData(Map map);
}
