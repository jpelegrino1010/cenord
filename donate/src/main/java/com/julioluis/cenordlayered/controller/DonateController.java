package com.julioluis.cenordlayered.controller;

import com.julioluis.cenordlayered.model.Donate;
import com.julioluis.cenordlayered.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("donates")
public class DonateController {

    @Autowired
    private DonateService donateService;

    @PostMapping
    public Donate create(@RequestBody Donate donate) {
        Donate response= donateService.save(donate);
        return response;
    }
}
