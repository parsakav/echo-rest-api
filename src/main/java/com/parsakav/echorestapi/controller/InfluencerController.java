package com.parsakav.echorestapi.controller;


import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.request.InfluencerRequest;
import com.parsakav.echorestapi.response.ErrorMessages;
import com.parsakav.echorestapi.response.InfluencerResponse;
import com.parsakav.echorestapi.service.InfluencerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Influencer", description = "Influencer management APIs")
@RequestMapping(path = "/api/influencer",consumes = {MediaType.APPLICATION_JSON_VALUE
        ,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE
        ,MediaType.APPLICATION_XML_VALUE})
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
    @PostMapping()
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
}
