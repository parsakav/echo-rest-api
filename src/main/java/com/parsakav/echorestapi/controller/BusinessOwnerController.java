package com.parsakav.echorestapi.controller;

import com.parsakav.echorestapi.dto.BusinessOwnerDTO;
import com.parsakav.echorestapi.dto.OfferDTO;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.request.BusinessOwnerRequest;
import com.parsakav.echorestapi.request.OfferRequest;
import com.parsakav.echorestapi.response.BusinessOwnerResponse;
import com.parsakav.echorestapi.response.ErrorMessages;
import com.parsakav.echorestapi.response.InfluencerResponse;
import com.parsakav.echorestapi.response.OfferResponse;
import com.parsakav.echorestapi.service.BusinessOwnerService;
import com.parsakav.echorestapi.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.LinkedList;
import java.util.List;

@RestController
@Tag(name = "Business owner", description = "Business owner management APIs")
@RequestMapping(path = "/api/business")
public class BusinessOwnerController {
    private final BusinessOwnerService influencerService;
    private final OfferService offerService;
    private static final Logger logger = LoggerFactory.getLogger(BusinessOwnerController.class);

    public BusinessOwnerController(BusinessOwnerService influencerService, OfferService offerService) {
        this.influencerService = influencerService;
        this.offerService = offerService;
    }

    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Save a business owner",
            description = "Save a business owner specifying its data.The response is what is stored"
            //  description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.",
            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "The date will be save and you receive business owner data",
                    content = { @Content(schema = @Schema(implementation = InfluencerResponse.class), mediaType = "application/json" )
                            ,@Content(schema = @Schema(implementation = InfluencerResponse.class),mediaType = "application/xml")
                            ,}
            ),
            @ApiResponse(responseCode = "400",content = { @Content(schema = @Schema(implementation = String.class), mediaType ="text/plain")}, description = "The data may already exist or may not be valid")}
    )
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE
            ,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE
            ,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BusinessOwnerResponse> save(@RequestBody @Valid BusinessOwnerRequest influencerRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.trace("Business owner validation failed");
            bindingResult.getAllErrors().forEach(System.out::println);
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        BusinessOwnerDTO dto = new BusinessOwnerDTO();
        BeanUtils.copyProperties(influencerRequest,dto);
        dto=influencerService.save(dto);
        BusinessOwnerResponse influencerResponse = new BusinessOwnerResponse();
        BeanUtils.copyProperties(dto,influencerResponse);

        return  ResponseEntity.ok(influencerResponse);
    }

    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Send an offer",
            description = "Send an offfer to a specific influencer by a specific business owner"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Ok will return of everything is ok  ",
                    content = { @Content(schema = @Schema(implementation = OfferResponse.class), mediaType = "application/json" )
                            ,}
            ), @ApiResponse(responseCode = "500",description = "If validation failed",
            content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain" )}

                    )}
    )
    @PostMapping("/offer")
    @PreAuthorize("hasRole('ROLE_BUISNESS')")

    public ResponseEntity<OfferResponse> makeAnOffer(@RequestBody @Valid OfferRequest offerRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.trace("OfferRequest validation failed");
throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        OfferDTO offerDTO = new OfferDTO();
        BeanUtils.copyProperties(offerRequest,offerDTO);

      OfferDTO rV=  offerService.save(offerDTO);
      OfferResponse rV1 = new OfferResponse();
      BeanUtils.copyProperties(rV,rV1);
        return ResponseEntity.ok(rV1);
    }
    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Get all offers",
            description = "Get offers that sent by specific business owner"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "List of offers will return if everything is ok  ",
                    content = { @Content(schema = @Schema(implementation = OfferResponse.class), mediaType = "application/json" )
                            ,}
            ), @ApiResponse(responseCode = "500",description = "If validation failed",
            content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain" )}

    )}
    )
    @GetMapping("/offer/{bownerPhoneNumber}")
    @PreAuthorize("hasRole('ROLE_BUISNESS')")

    public ResponseEntity<List<OfferResponse>> getَAllOffers(@PathVariable("bownerPhoneNumber") long bownerPhoneNumber){

        String rolePhoneNumber=(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if(!rolePhoneNumber.trim().equals(bownerPhoneNumber+"")){
          throw new RuntimeException("Forbidden");
      }
        List<OfferResponse> commentDTOS = new LinkedList<>();
        List<OfferDTO> save = offerService.getAllOffers(bownerPhoneNumber);
        for(OfferDTO c: save) {
            OfferResponse rv = new OfferResponse();
            BeanUtils.copyProperties(c, rv);
            rv.setPhoneNumber(c.getInfluencerPhoneNumber());
            commentDTOS.add(rv);
        }
        return ResponseEntity.ok(commentDTOS);
    }
  
}
