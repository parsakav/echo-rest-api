package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.CommentDTO;
import com.parsakav.echorestapi.entity.Comment;
import com.parsakav.echorestapi.entity.Offer;
import com.parsakav.echorestapi.repository.CommentRepository;
import com.parsakav.echorestapi.repository.OfferRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO, long phoneNumber) {
     Offer o=   offerRepository.findById((int) commentDTO.getOfferId()).get();
        if(o.getBusinessOwner().getPhoneNumber()!=phoneNumber){
            return null;
        }
     if(o==null || o.isAccept()==null||!o.isAccept()){
         return null;
     }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setOffer(o);
        Comment save = commentRepository.save(comment);
        CommentDTO rv = new CommentDTO();

        BeanUtils.copyProperties(save,rv);
        return rv;
    }   @Override
    public List<CommentDTO> getComments(long phoneNumber) {
   List<CommentDTO> commentDTOS = new LinkedList<>();
        List<Comment> save = commentRepository.getComments(phoneNumber);
        for(Comment c: save) {
            CommentDTO rv = new CommentDTO();
            BeanUtils.copyProperties(c, rv);
            rv.setOfferId(c.getOffer().getId());
            rv.setCommentId(c.getId());
            commentDTOS.add(rv);
        }
        return commentDTOS;
    }
    @Override
    public List<CommentDTO> getAcceptedComments(long phoneNumber) {
   List<CommentDTO> commentDTOS = new LinkedList<>();
        List<Comment> save = commentRepository.getAcceptedComments(phoneNumber);
        for(Comment c: save) {
            CommentDTO rv = new CommentDTO();
            BeanUtils.copyProperties(c, rv);
            rv.setOfferId(c.getOffer().getId());
            rv.setCommentId(c.getId());

            commentDTOS.add(rv);
        }
        return commentDTOS;
    }

    @Override
    public List<CommentDTO> getBusinessOwnerComments(long phoneNumber) {
        List<CommentDTO> commentDTOS = new LinkedList<>();
        List<Comment> save = commentRepository.getBussinessOwnerComments(phoneNumber);
        for(Comment c: save) {
            CommentDTO rv = new CommentDTO();
            BeanUtils.copyProperties(c, rv);
            rv.setOfferId(c.getOffer().getId());
            rv.setCommentId(c.getId());

            commentDTOS.add(rv);
        }
        return commentDTOS;
    }

    @Override
    public List<CommentDTO> getAcceptedBusinessOwnerComments(long phoneNumber) {
        List<CommentDTO> commentDTOS = new LinkedList<>();
        List<Comment> save = commentRepository.getBusinessOwnerAcceptedComments(phoneNumber);
        for(Comment c: save) {
            CommentDTO rv = new CommentDTO();
            BeanUtils.copyProperties(c, rv);
            rv.setOfferId(c.getOffer().getId());
            rv.setCommentId(c.getId());

            commentDTOS.add(rv);
        }
        return commentDTOS;

    }
}
