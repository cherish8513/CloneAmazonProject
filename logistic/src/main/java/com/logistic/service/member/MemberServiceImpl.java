package com.logistic.service.member;

import com.logistic.domain.member.Member;
import com.logistic.dto.member.request.ChangeMemberReqDto;
import com.logistic.dto.member.request.SaveMemberReqDto;
import com.logistic.repository.member.JpaMemberRepository;
import com.logistic.service.util.ValidationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
