package com.parsakav.echorestapi.controller;


import com.parsakav.echorestapi.dto.OfferDTO;
import com.parsakav.echorestapi.response.OfferResponse;
import com.parsakav.echorestapi.service.OfferService;
import com.parsakav.echorestapi.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/utils")
@Tag(name = "Utils", description = "Utility api")

public class UtilsController {

    @Autowired
   private RoleService roleService;
    @Autowired
   private OfferService offerService;
    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "find role related by a phoneNumber",
            description = "It's clear"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "return user role",
                    content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain" )
                            ,}
            )}
    )
    @GetMapping("/role/{phoneNumber}")
    public String findRole(@PathVariable("phoneNumber") String phoneNumber){
       return roleService.findRole(phoneNumber);
    }

    @Operation(
            //summary = "Retrieve a Tutorial by Id",
            summary = "Pay an offer with specifc offer id",
            description = "It's clear"

            /*    tags = *//*{ "Influencer", "Post" }*//*"Influencer"*/)
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OfferResponse will return",
                    content = { @Content(schema = @Schema(implementation = OfferResponse.class), mediaType = "text/plain" )
                            ,}
            )}
    )
    @PostMapping("/pay/{id}")
    public OfferResponse pay(@PathVariable("id") int offer_id){
        OfferResponse offerResponse = new OfferResponse();
        OfferDTO pay = offerService.pay(offer_id);
        BeanUtils.copyProperties(offerResponse,pay);
        return offerResponse;
    }
}
