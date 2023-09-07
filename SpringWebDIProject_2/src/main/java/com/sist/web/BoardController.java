package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	//목록
	@GetMapping("list.do")//출력만 하기 때문에 getmapping
	public String board_list(String page,Model model) // page는 첨에 null이 들어오기 때문에 String, 상세 보기 같은 경우는 데이터형에 맞게
	{
		//Model model => 전송 객체(request대신 사용)
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowsize=10;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalpage();
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		return "board/list";
	}
	//데이터 추가
	@GetMapping("insert.do")
	public String board_insert()
	{
		return "board/insert";
	}
	@PostMapping("insert_ok.do") //에러날 경우 411
	public String board_insert_ok(BoardVO vo)
	{
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	//데이터 수정
	@GetMapping("update.do")
	public String board_update(int no,Model model)
	{
		BoardVO vo = dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	//데이터 삭제
	@GetMapping("delete.do")
	public String board_deletr(int no,Model model)
	{
		
		model.addAttribute("no",no);
		return "board/delete";
	}
	

	@PostMapping("delete_ok.do")
	//@ResponseBody //restcontroller 처럼 문자열 보내지게 해줌
	public String board_delete_ok(int no,String pwd,Model model)
	{
		boolean bcheck=dao.boardDelete(no, pwd);
		model.addAttribute("bcheck",bcheck);
		
		return "board/delete_ok";
	}
	//상세보기
	@GetMapping("detail.do")
	public String board_detail(int no,Model model)
	{	
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	/*
	 *  1. Spring MVC 
	 *  	1)DispatcherServlet 등록 => web.xml
	 *  	=> 클래스를 등록한 파일 셋팅(클래스 관리)
	 *  	=> 한글 변환
	 *  	2) 클래스 제작
	 *  		=> VO
	 *  		=> Mapper
	 *  		=> DAO
	 *  		=> Model
	 * 		3) application.xml (클래스 등록)
	 * 		4) JSP
	 * 	-----------------------------------------------
	 */
	
	//검색 ==> 동적 쿼리
}
