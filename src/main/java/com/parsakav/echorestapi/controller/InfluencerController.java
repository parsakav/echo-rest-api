package com.parsakav.echorestapi.controller;


import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.dto.OfferDTO;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.request.InfluencerRequest;
import com.parsakav.echorestapi.response.ErrorMessages;
import com.parsakav.echorestapi.response.InfluencerResponse;
import com.parsakav.echorestapi.service.InfluencerService;
import com.parsakav.echorestapi.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private OfferService offerService;
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
//    @PreAuthorize("hasRole('ROLE_INFLUENCER')")


 @PreAuthorize("hasAnyRole('ROLE_BUISNESS','ROLE_INFLUENCER')")
    @GetMapping(produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
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

    @PreAuthorize("hasAnyRole('ROLE_INFLUENCER','ROLE_BUISNESS')")

  //  @PreAuthorize("hasRole('ROLE_INFLUENCER')")
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

    @PreAuthorize("hasRole('ROLE_INFLUENCER')")
    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Retrieve offers related to a influencer phonenumber",
            description = "It's clear"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "The OfferDTO objects(as json/xml) will be return",
                    content = { @Content(schema = @Schema(implementation = InfluencerResponse.class), mediaType = "application/json" )
                            ,@Content(schema = @Schema(implementation = InfluencerResponse.class),mediaType = "application/xml")
                            ,}
            )}
    )
    @GetMapping(path = "/offers/{phoneNumber}",produces = {MediaType.APPLICATION_JSON_VALUE
            ,MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity<List<OfferDTO>> offers(@PathVariable("phoneNumber") String phoneNumber){

        return ResponseEntity.ok(offerService.getOffers(phoneNumber));
    } @GetMapping(path = "/offers/accept/{phoneNumber}",produces = {MediaType.APPLICATION_JSON_VALUE
            ,MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity<List<OfferDTO>> acceptedOffers(@PathVariable("phoneNumber") String phoneNumber){

        return ResponseEntity.ok(offerService.getAcceptedOffers(phoneNumber));
    }
    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Reject an offer by phonenumber and offer id",
            description = "The offer id is a virtual attribute that auto incremented one by one and when you see all offerDtos you will see the id's"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Ok will return of everything is ok (if offer has been reject) or return not found if there isn't offer related to offer id",
                    content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain" )
                            ,}
            )}
    )
@PreAuthorize("hasRole('ROLE_INFLUENCER')")
    @GetMapping(path = "/offers/reject/{phoneNumber}/{id}")
    public ResponseEntity<String> rejectAnOffer(@PathVariable("phoneNumber") String phoneNumber,@PathVariable("id") int id){
       return ResponseEntity.ok (offerService.rejectAnOffer(phoneNumber,id)?"Ok":"Not found");


    }
    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Agree an offer by phonenumber and offer id",
            description = "The offer id is a virtual attribute that auto incremented one by one and when you see all offerDtos you will see the id's"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Ok will return of everything is ok  or return not found if there isn't offer related to offer id",
                    content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain" )
                            ,}
            )}
    )
    @PreAuthorize("hasRole('ROLE_INFLUENCER')")
    @GetMapping(path = "/offers/agree/{phoneNumber}/{id}")
    public ResponseEntity<String> agreeAnOffer(@PathVariable("phoneNumber") String phoneNumber, @PathVariable("id") int id){
       return ResponseEntity.ok (offerService.rejectAnOffer(phoneNumber,id)?"Ok":"Not found");


    }


}
