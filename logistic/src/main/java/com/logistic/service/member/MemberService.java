package com.logistic.service.member;

import com.logistic.dto.member.request.ChangeMemberReqDto;
import com.logistic.dto.member.request.SaveMemberReqDto;

public interface MemberService {
    public Long join(SaveMemberReqDto requestDto);
    public Long changePassword(ChangeMemberReqDto requestDto);
}
