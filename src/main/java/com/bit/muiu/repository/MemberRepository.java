package com.bit.muiu.repository;

import com.bit.muiu.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    long countByUsername(String username);

    List<Member> findByStatus(String status);
}
