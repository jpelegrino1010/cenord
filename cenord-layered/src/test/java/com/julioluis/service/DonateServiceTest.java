package com.julioluis.service;

import com.julioluis.cenordlayered.model.Donate;
import com.julioluis.cenordlayered.repository.DonateRepository;
import com.julioluis.cenordlayered.service.DonateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DonateServiceTest {

    @InjectMocks
    private DonateService service;
    @Mock
    private DonateRepository donateRepository;

    @Before
    public void init() {

    }

    @Test
    public void save_donate_entity_sucessfull() {

        Donate donate=new Donate();
        donate.setComment("No hubo problemas");
        donate.setAmount(BigDecimal.valueOf(5000));

        when(donateRepository.save(any(Donate.class))).thenReturn(persistenceEntity(donate));
        Donate donateSaved=service.save(donate);

        assertNotNull(donateSaved);
        verify(donateRepository,times(1)).save(any(Donate.class));
    }


    private Donate persistenceEntity(Donate donate) {
        donate.setId(1L);
        return donate;
    }
}
