package com.community.community_board.service;

import com.community.community_board.domain.BoardDTO;
import com.community.community_board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;
    /**
     * idx가 null이면 insertBoard 사용해서 정상적으로 들어가면 true 저장, int queryResult니까 1로 저장 됨.
     * idx가 있으면 updateBoard를 해줘야 하므로, updateBoard 실행, 마찬가지로 성공하면 true(1) 저장
     * query result 가 존재하면 true 를 반환하고 성공적으로 완료됐다고 알려줌.
     *  @param params
     * @return
     */
    @Override
    public boolean registerBoard(BoardDTO params) {
        int queryResult = 0;

        if(params.getIdx() == null){
            queryResult = boardMapper.insertBoard(params);
        }else {
            queryResult = boardMapper.updateBoard(params);
        }
        return (queryResult == 1 ) ? true: false;
    }

    /**
     * 게시글의 자세한 내용을 보는 부분
     *
     * @param idx
     * @return
     */
    @Override
    public BoardDTO getBoardDetail(Long idx) {
        return boardMapper.selectBoardDetail(idx);
    }

    /**
     * 해당 idx를 가진 부분을 selectBoardDetail(idx)로 반환하고
     * 그것을 board에 저장해서 DeleteYn 부분을 Y로 만들어줌.
     * @param idx
     * @return
     */
    @Override
    public boolean deleteBoard(Long idx) {
        int queryResult = 0;
        BoardDTO board = boardMapper.selectBoardDetail(idx);

        if(board != null && "N".equals(board.getDeleteYn())){
            queryResult = boardMapper.deleteBoard(idx);
        }

        return (queryResult == 1) ? true: false;
    }

    /**
     * 비어있는 리스트를 선언한후에 게시물 수가 0 초과이면 모든 BoardList들을 리스트에 저장해줌
     * 리스트는 BoardDTO 형식으로 선언 되어있으므로 넣어주면 됨.
     * @return
     */
    @Override
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardList = Collections.emptyList();


        int boardTotalCount = boardMapper.selectBoardTotalCount();
        if (boardTotalCount > 0) {
            boardList = boardMapper.selectBoardList();
        }

        return boardList;
    }

}
