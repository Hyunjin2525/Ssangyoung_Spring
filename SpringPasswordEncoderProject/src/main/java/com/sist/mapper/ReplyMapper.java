package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.ReplyVO;


public interface ReplyMapper {
	// <select id="replyListData" resultType="replyVO" parameterType="int">
	 //	SELECT no,fno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday FROM springTestreply WHERE fno=#{fno} OREDER BY no DESC
	// </select>
	public List<ReplyVO> replyListData(int fno);

	 //	<insert id="replyInsert" parameterType="ReplyVO">
	 		//INSERT INTO springTestReply VALUES(str_no_seq.nextval,#{fno},#{id},#{name},#{msg},SYSDATE)
	 	//</insert>
	public void replyInsert(ReplyVO vo);
	
	@Delete("DELETE FROM springTestReply WHERE no=#{no}")
	public void replyDelete(int no);
	
	@Update("UPDATE springTestReply SET msg=#{msg} WHERE no=#{no}")
	public void replyUpdate(ReplyVO vo);
}
