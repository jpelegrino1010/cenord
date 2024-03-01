package com.julioluis.noahrdsystem.repositories;

import com.julioluis.noahrdsystem.model.RolAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolAuthorityRepository extends JpaRepository<RolAuthority,Long> {
}
