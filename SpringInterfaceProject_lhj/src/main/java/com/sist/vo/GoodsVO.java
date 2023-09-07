package com.sist.vo;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class GoodsVO {
	private int gno, cprice, price, cno;
	private String goods_name, goods_image, goods_detail, brand, origin, dbcprice, dbprice;
}
