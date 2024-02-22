package com.julioluis.noahrdsystem.controllers;

import com.julioluis.noahrdsystem.model.Member;
import com.julioluis.noahrdsystem.model.Rol;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!!";
    }

    @PostMapping("/send")
    public String send(@RequestBody Rol rol) {
        return "sending... " + rol;
    }
}
