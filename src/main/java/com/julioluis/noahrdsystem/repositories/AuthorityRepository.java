package com.julioluis.noahrdsystem.repositories;

import com.julioluis.noahrdsystem.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
