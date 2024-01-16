package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO addComment(CommentDTO commentDTO, long phoneNumber);

    public List<CommentDTO> getComments(long phoneNumber);
    public List<CommentDTO> getAcceptedComments(long phoneNumber);
    public List<CommentDTO> getBusinessOwnerComments(long phoneNumber);
    public List<CommentDTO> getAcceptedBusinessOwnerComments(long phoneNumber);
}

