package com.logistic.repository.member;

import com.logistic.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
}
