package com.julioluis.noahrdsystem.controllers;

import com.julioluis.noahrdsystem.model.Member;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/donates")
public class DonateController {


    @PreAuthorize("hasAuthority('DONATE')")
    @GetMapping
    public String getAllDonate() {
        return "All donate";
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('DONATE','MEMBER')")
    public String getDonateById(@PathVariable(value = "id") Long donateId) {
        return "Your donate is the corresponding id "+ donateId;
    }

}
