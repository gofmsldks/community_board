package com.community.community_board.mapper;

import java.util.List;

import com.community.community_board.domain.BoardDTO;
import com.community.community_board.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface BoardMapper {

    public int insertBoard(BoardDTO params);

    public BoardDTO selectBoardDetail(Long idx);

    public int updateBoard(BoardDTO params);

    public int deleteBoard(Long idx);

    public List<BoardDTO> selectBoardList(BoardDTO params);

    public int selectBoardTotalCount(BoardDTO params);


}