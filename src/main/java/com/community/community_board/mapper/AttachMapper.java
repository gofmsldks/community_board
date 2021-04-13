package com.community.community_board.mapper;

import com.community.community_board.domain.AttachDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachMapper {

    public int insertAttach(List<AttachDTO> attachList);

    public AttachDTO selectAttachDetail(Long idx);

    public int deleteAttach(Long boardIdx);

    public List<AttachDTO> selectAttachList(Long boardIdx);

    public int selectAttachTotalCount(Long boardIdx);

    public int undeleteAttach(List<Long> idxs);

}
