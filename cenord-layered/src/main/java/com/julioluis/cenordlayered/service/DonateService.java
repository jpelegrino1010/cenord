package com.julioluis.cenordlayered.service;

import com.julioluis.cenordlayered.model.Donate;
import com.julioluis.cenordlayered.repository.DonateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonateService {
    @Autowired
    private DonateRepository donateRepository;

    public Donate save(Donate donate) {
        return donateRepository.save(donate);
    }
}
