package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

/*
 * NO
TITLE
POSTER
CHEF
LINK
HIT
 */
@Getter
@Setter
public class RecipeVO {
	private int no,hit;
	private String poster,title,chef,link;
}
