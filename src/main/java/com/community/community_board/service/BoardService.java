package com.community.community_board.service;

import com.community.community_board.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    public boolean registerBoard(BoardDTO params);

    public BoardDTO getBoardDetail(Long idx);

    public boolean deleteBoard(Long idx);

    public List<BoardDTO> getBoardList();
}
