package com.parsakav.echorestapi.controller;

import com.parsakav.echorestapi.dto.CommentDTO;
import com.parsakav.echorestapi.dto.OfferDTO;
import com.parsakav.echorestapi.entity.Comment;
import com.parsakav.echorestapi.request.CommentRequest;
import com.parsakav.echorestapi.response.CommentResponse;
import com.parsakav.echorestapi.response.ErrorMessages;
import com.parsakav.echorestapi.response.OfferResponse;
import com.parsakav.echorestapi.security.SecurityConstant;
import com.parsakav.echorestapi.service.CommentService;
import com.parsakav.echorestapi.service.OfferService;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@Tag(name = "Comment", description = "Comment management APIs")

@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private OfferService offerService;
    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Add comment to a specific offer",
            description = "clear"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "CommentResponse will return of everything is ok ",
                    content = { @Content(schema = @Schema(implementation = CommentResponse.class), mediaType = "application/json" )
                            ,}
            )}
    )
    @PreAuthorize("hasRole('ROLE_BUISNESS')")
    @PostMapping()
    public ResponseEntity<CommentResponse> addComment(@RequestBody @Valid CommentRequest cr, BindingResult bindingResult){


        long phoneNumber= Long.valueOf((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(cr);
        if(bindingResult.hasErrors()){
        System.out.println(bindingResult.getAllErrors().toString());
        System.out.println(bindingResult.getFieldError());
        throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(cr,commentDTO);
        commentDTO = commentService.addComment(commentDTO,phoneNumber);
        CommentResponse returnValue = new CommentResponse();
        BeanUtils.copyProperties(commentDTO,returnValue);

        return ResponseEntity.ok(returnValue);
    }

    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Get offer that doesn't have any comment",
            description = "clear"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "List of offer response will return if everything is ok ",
                    content = { @Content(schema = @Schema(implementation = OfferResponse.class), mediaType = "application/json" )
                            ,}
            )}
    )
    @GetMapping("/offer/{bownerPhoneNumber}")

    public ResponseEntity<List<OfferResponse>> getUnCommentedOffers(@PathVariable("bownerPhoneNumber") long bownerPhoneNumber){

        List<OfferResponse> commentDTOS = new LinkedList<>();
        List<OfferDTO> save = offerService.getUnCommentedOffers(bownerPhoneNumber);
        for(OfferDTO c: save) {
            OfferResponse rv = new OfferResponse();

            BeanUtils.copyProperties(c, rv);
            rv.setPhoneNumber(c.getInfluencerPhoneNumber());

            commentDTOS.add(rv);
        }
        return ResponseEntity.ok(commentDTOS);
    }
    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Get influencer comments",
            description = "clear"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "List of comments related to the influencer",
                    content = { @Content(schema = @Schema(implementation = CommentResponse.class), mediaType = "application/json" )
                            ,}
            )}
    )
   // @PreAuthorize("hasRole('ROLE_BUISNESS')")
@GetMapping("{influencerPhonenumber}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable("influencerPhonenumber") long influecerPhoneNumber){

        List<CommentResponse> commentDTOS = new LinkedList<>();
        List<CommentDTO> save = commentService.getComments(influecerPhoneNumber);
        for(CommentDTO c: save) {
            CommentResponse rv = new CommentResponse();

            BeanUtils.copyProperties(c, rv);
            commentDTOS.add(rv);
        }
        return ResponseEntity.ok(commentDTOS);
    }
}
