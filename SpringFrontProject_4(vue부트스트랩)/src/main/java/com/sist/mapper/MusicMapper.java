package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.MusicVO;

public interface MusicMapper {
	
	@Select("SELECT * FROM genie_music WHERE cno=#{cno} ORDER BY mno ASC")
	public List<MusicVO> musicListData(int cno);
	
	@Select("SELECT * FROM genie_music WHERE mno=#{mno}")
	public MusicVO musicDetailData(int mno);
}
