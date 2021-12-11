package com.amazonClone.logisticSystem.service.member;

import com.amazonClone.logisticSystem.domain.member.Member;
import com.amazonClone.logisticSystem.dto.member.request.SaveRequestDto;
import com.amazonClone.logisticSystem.dto.member.request.ChangeRequestDto;
import com.amazonClone.logisticSystem.repository.member.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final JpaMemberRepository memberRepository;

    @Override
    @Transactional
    public Long join(SaveRequestDto requestDto) {
        validateDuplicate(requestDto);
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long changePassword(ChangeRequestDto requestDto) {
        Member findMember = getMember(memberRepository.findByEmail(requestDto.getEmail()));
        findMember.changePassword(requestDto.getPassword());
        return findMember.getId();
    }

    // 중복 회원 검사
    private void validateDuplicate(SaveRequestDto requestDto){
        memberRepository.findByEmail(requestDto.getEmail())
                .ifPresent(member -> {
                    throw new IllegalStateException("이미 존재하는 이메일 입니다.");
                });
    }

    // 유효한 회원인지 검증
    private Member getMember(Optional<Member> byEmail) {
        return byEmail.orElseThrow(() -> new IllegalArgumentException("없는 회원 입니다"));
    }
}
