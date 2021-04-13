package com.community.community_board.service;

import java.util.List;

import com.community.community_board.domain.CommentDTO;

public interface CommentService {

    public boolean registerComment(CommentDTO params);

    public boolean deleteComment(Long idx);

    public List<CommentDTO> getCommentList(CommentDTO params);

}
