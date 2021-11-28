package com.amazonClone.logisticSystem.service.member;

import com.amazonClone.logisticSystem.domain.member.Member;
import com.amazonClone.logisticSystem.dto.member.SaveRequestDto;
import com.amazonClone.logisticSystem.dto.member.ChangeRequestDto;
import com.amazonClone.logisticSystem.repository.member.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final JpaMemberRepository memberRepository;


    @Override
    public Long join(SaveRequestDto requestDto) {
        validateDuplicate(requestDto);
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    public Long changePassword(ChangeRequestDto requestDto) {
        Member findMember = getMember(memberRepository.findByEmail(requestDto.getEmail()));
        findMember.changePassword(requestDto.getPassword());
        return findMember.getId();
    }

    private void validateDuplicate(SaveRequestDto requestDto){
        memberRepository.findByEmail(requestDto.getEmail())
                .ifPresent(member -> {
                    throw new IllegalStateException("이미 존재하는 이메일 입니다.");
                });
    }

    private Member getMember(Optional<Member> byEmail) {
        return byEmail.orElseThrow(() -> new IllegalArgumentException("없는 회원 입니다"));
    }
}
