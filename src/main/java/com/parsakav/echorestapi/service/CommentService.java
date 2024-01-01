package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO addComment(CommentDTO commentDTO, long phoneNumber);

    public List<CommentDTO> getComments(long phoneNumber);
}

