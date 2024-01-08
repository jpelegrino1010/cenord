package com.julioluis.cenordlayered.repository;

import com.julioluis.cenordlayered.model.Donate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateRepository extends JpaRepository<Donate,Long> {
}
