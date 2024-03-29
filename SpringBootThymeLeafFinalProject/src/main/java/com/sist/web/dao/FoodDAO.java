package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.FoodEntity;

@Repository
public interface FoodDAO extends JpaRepository<FoodEntity, Integer>{
	@Query(value = "SELECT * FROM food_location WHERE address LIKE CONCAT('%',:address,'%') LIMIT :start,20",nativeQuery = true)
	public List<FoodEntity> foodFindListData(@Param("address") String address,@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM food_location WHERE address LIKE CONCAT('%',:address,'%')",nativeQuery = true)
	public int foodFindTotal(String address);
}
