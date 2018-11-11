package com.xxin.demo.dwr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class DwrController {
    @RequestMapping("/demo")
    public String getDemo(){
        return "/dwr/demo";
    }
}
