package com.julioluis.noahrdsystem.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DonateController {

    @GetMapping("/donates")
    @PreAuthorize("hasAuthority('DONATE')")
    public String getAllDonate() {
        return "All donate";
    }

    @GetMapping("donates/{id}")
    @PreAuthorize("hasAnyAuthority('DONATE','MEMBER')")
    public String getDonateById(@PathVariable(value = "id") Long donateId) {
        return "Your donate is the corresponding id "+ donateId;
    }
}
