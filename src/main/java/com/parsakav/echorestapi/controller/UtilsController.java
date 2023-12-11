package com.parsakav.echorestapi.controller;


import com.parsakav.echorestapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/utils")
public class UtilsController {

    @Autowired
   private RoleService roleService;
    @GetMapping("{phoneNumber}")
    public String findRole(@PathVariable("phoneNumber") String phoneNumber){
       return roleService.findRole(phoneNumber);
    }
}
