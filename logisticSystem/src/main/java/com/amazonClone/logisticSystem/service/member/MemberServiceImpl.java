package com.amazonClone.logisticSystem.service.member;

import com.amazonClone.logisticSystem.domain.member.Member;
import com.amazonClone.logisticSystem.dto.member.request.SaveMemberReqDto;
import com.amazonClone.logisticSystem.dto.member.request.ChangeMemberReqDto;
import com.amazonClone.logisticSystem.repository.member.JpaMemberRepository;
import com.amazonClone.logisticSystem.service.util.ValidationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final JpaMemberRepository memberRepository;
    private final ValidationCheck validationCheck;

    @Override
    @Transactional
    public Long join(SaveMemberReqDto requestDto) {
        validationCheck.validateDuplicate(memberRepository.findByEmail(requestDto.getEmail()));
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long changePassword(ChangeMemberReqDto requestDto) {
        Member findMember = validationCheck.getMember(memberRepository.findByEmail(requestDto.getEmail()));
        findMember.changePassword(requestDto.getPassword());
        return findMember.getId();
    }
}
