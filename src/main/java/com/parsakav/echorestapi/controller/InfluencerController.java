package com.parsakav.echorestapi.controller;


import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.entity.Influencer;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.request.InfluencerRequest;
import com.parsakav.echorestapi.response.ErrorMessages;
import com.parsakav.echorestapi.response.InfluencerResponse;
import com.parsakav.echorestapi.service.InfluencerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.LinkedList;
import java.util.List;

@RestController
@Tag(name = "Influencer", description = "Influencer management APIs")
@RequestMapping(path = "/api/influencer")
public class InfluencerController {
    @Autowired
    private InfluencerService influencerService;
    private static final Logger logger = LoggerFactory.getLogger(InfluencerController.class);

    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Save an influencer",
          description = "Save an influencer specifying its data.The response is what is stored"
          //  description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.",
        /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
  @ApiResponses({
           @ApiResponse(responseCode = "200",description = "The date will be save and you receive influencer data",
                   content = { @Content(schema = @Schema(implementation = InfluencerResponse.class), mediaType = "application/json" )
                           ,@Content(schema = @Schema(implementation = InfluencerResponse.class),mediaType = "application/xml")
           ,}
           ),
          @ApiResponse(responseCode = "400",content = { @Content(schema = @Schema(implementation = String.class), mediaType ="text/plain")}, description = "The data may already exist or may not be valid")}
  )
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE
            ,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE
            ,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<InfluencerResponse> save(@RequestBody @Valid InfluencerRequest influencerRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            logger.trace("Influencer validation faild");
            bindingResult.getAllErrors().forEach(System.out::println);
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        InfluencerDTO dto = new InfluencerDTO();
        BeanUtils.copyProperties(influencerRequest,dto);
        dto=influencerService.save(dto);
        InfluencerResponse influencerResponse = new InfluencerResponse();
        BeanUtils.copyProperties(dto,influencerResponse);

        return  ResponseEntity.ok(influencerResponse);
    }

    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Retrieve all influencers ",
            description = "It's clear"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "The InfluencerResponse objects(as json/xml) will be return",
                    content = { @Content(schema = @Schema(implementation = InfluencerResponse.class), mediaType = "application/json" )
                            ,@Content(schema = @Schema(implementation = InfluencerResponse.class),mediaType = "application/xml")
                            ,}
            ),
            @ApiResponse(responseCode = "403",content = { @Content(schema = @Schema(implementation = String.class), mediaType ="text/plain")}, description = "If token wasn't valid")}
    )


    @GetMapping(path = "/search/{followers}",produces = {MediaType.APPLICATION_JSON_VALUE
            ,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<InfluencerResponse>> get(@PathVariable("followers")int followers){
        List<InfluencerResponse> influencerResponses = new LinkedList<>();
        List<InfluencerDTO> all = influencerService.findAllByFollowers(followers);
        for(InfluencerDTO a:all){
            InfluencerResponse returnValue=new InfluencerResponse();


            BeanUtils.copyProperties(a, returnValue);
            influencerResponses.add(returnValue);
        }
        ResponseEntity<List<InfluencerResponse>> response=new   ResponseEntity<>(influencerResponses, HttpStatus.OK);
        return response;
    }
    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Retrieve all influencers ",
              description = "It's clear"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "The InfluencerResponse objects(as json/xml) will be return",
                    content = { @Content(schema = @Schema(implementation = InfluencerResponse.class), mediaType = "application/json" )
                            ,@Content(schema = @Schema(implementation = InfluencerResponse.class),mediaType = "application/xml")
                            ,}
            ),
            @ApiResponse(responseCode = "403",content = { @Content(schema = @Schema(implementation = String.class), mediaType ="text/plain")}, description = "If token wasn't valid")}
    )


    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE
            ,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<InfluencerResponse>> get(){
        List<InfluencerResponse> influencerResponses = new LinkedList<>();
        List<InfluencerDTO> all = influencerService.findAll();
        for(InfluencerDTO a:all){
            InfluencerResponse returnValue=new InfluencerResponse();


            BeanUtils.copyProperties(a, returnValue);
            influencerResponses.add(returnValue);
        }
      ResponseEntity<List<InfluencerResponse>> response=new   ResponseEntity<>(influencerResponses, HttpStatus.OK);
       return response;
    }
    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Retrieve an influencer by phonenumber ",
            description = "Get a InfluencerResponse object by specifying authorization header . The response is InfluencerResponse object "

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(/*headers = {@Header(name = "Authorization",required = true,description = "The Value must be influencer token")}*/
                    responseCode = "200",description = "The InfluencerResponse object will be return",
                    content = { @Content(schema = @Schema(implementation = InfluencerResponse.class), mediaType = "application/json" )
                            ,@Content(schema = @Schema(implementation = InfluencerResponse.class),mediaType = "application/xml")
                            ,}
            ),
            @ApiResponse(responseCode = "403",content = { @Content(schema = @Schema(implementation = String.class), mediaType ="text/plain")}, description = "If token wasn't valid")}
    )

    @GetMapping(path = "{phoneNumber}",produces = {MediaType.APPLICATION_JSON_VALUE
            ,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<InfluencerResponse> get(@PathVariable("phoneNumber") String phoneNumber){
        //UsernamePasswordAuthenticationToken u = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        InfluencerDTO influencerDTO=influencerService.findByPhonenumber(Long.parseLong(phoneNumber));
        if(influencerDTO!=null) {
            InfluencerResponse influencerResponse = new InfluencerResponse();
            BeanUtils.copyProperties(influencerDTO,influencerResponse);
            return ResponseEntity.ok().body(influencerResponse);
        } return ResponseEntity.notFound().build();
    }


}
