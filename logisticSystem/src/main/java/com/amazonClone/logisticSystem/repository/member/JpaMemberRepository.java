package com.amazonClone.logisticSystem.repository.member;

import com.amazonClone.logisticSystem.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
}
