package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.BoardMapper;
import com.sist.vo.BoardVO;
@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map)
	{
		return mapper.boardListData(map);
	}
	
	public int boardTotalpage()
	{
		return mapper.boardTotalpage();
	}
	//추가
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	//상세보기
	
	public BoardVO boardDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	//답변 ==== 트랜잭션 (스프링 : AOP)
	/*public BoardVO boardParentInfoData(int no);
	
	public void boardGroupStepIncrement(BoardVO vo);
	
	public void boardReplyInsert(BoardVO vo);
	
	@Update("UPDATE springReplyBoad SET depth=depth+1 WHERE no=#{no}")
	public void boardDepthIncrement(int no);*/
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void boardReplyInsert(int root,BoardVO vo)
	{
		BoardVO pvo=mapper.boardParentInfoData(root);
		mapper.boardGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(root);
		mapper.boardReplyInsert(vo);
		mapper.boardDepthIncrement(root);
	}
	
	public BoardVO boardUpdateData(int no)
	{
		return mapper.boardDetailData(no);
	}
	
	public boolean boardUpdate(BoardVO vo)
	{
		boolean bcheck=false;
		
		String db_pwd=mapper.boardPwd(vo.getNo());
		
		if(db_pwd.equals(vo.getPwd()))
		{
			bcheck=true;
			mapper.boardUpdate(vo);
		}
		return bcheck;
	}
	/*
	 *  1.트랜잭션 
	 *  	= 여러개의 SQL문장(DML=INSERT,UPDATE,DELETE)을 하나의 그룹으로 묶어서 처리하는 단위
	 *  	= 물리적으로는 여러개의 SQL문장 수행 => 논리적으로 하나의 작업으로 인식
	 *  	= 일괄처리
	 *  2.<tx:annotation-driven/>
		<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
		p:dataSource-ref="ds"/>
		3. 전파
			Propagation.REQUIRED : 기본형
				트랜잭션을 사용중이면 => 다음에 다시 재사용
				=> 시작할때만 한번 생성
				public void delete()
			Propagation.REQUIRED_NEW : 무조건 새롭게 생성
			Propagation.NAVER : 트랜잭셕을 무조건 설정
	 * 		
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public boolean boardDelete(int no,String pwd)
	{
		boolean bcheck=false;
		//비밀번호 읽기
		String db_pwd=mapper.boardPwd(no);
		BoardVO vo=mapper.boardInfoData(no);
		if(db_pwd.equals(pwd))
		{
			bcheck=true;
			//삭제할 수 있는 게시물인지 확인
			if(vo.getDepth()==0)
			{
				mapper.boardDelete(no);
			}else {
				mapper.boardSubjectUpdate(no);
			}
			mapper.boardDepthDecrement(vo.getRoot());
			
		}else {
			bcheck=false;
		}
		return bcheck;
	}
	
	public List<BoardVO> boardFindData(Map map)
	{
		return mapper.boardFindData(map);
	}
}
