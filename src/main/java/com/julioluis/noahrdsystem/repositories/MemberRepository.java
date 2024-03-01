package com.julioluis.noahrdsystem.repositories;

import com.julioluis.noahrdsystem.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByEmail(String email);
    Optional<Member> findById(Long id);
}
