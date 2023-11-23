package com.parsakav.echorestapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Business owner", description = "Business owner management APIs")
@RequestMapping(path = "/api/business")
public class BusinessOwnerController {

}
