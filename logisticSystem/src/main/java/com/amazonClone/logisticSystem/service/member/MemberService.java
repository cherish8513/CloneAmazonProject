package com.amazonClone.logisticSystem.service.member;

import com.amazonClone.logisticSystem.dto.member.request.ChangeMemberReqDto;
import com.amazonClone.logisticSystem.dto.member.request.SaveMemberReqDto;

public interface MemberService {
    public Long join(SaveMemberReqDto requestDto);
    public Long changePassword(ChangeMemberReqDto requestDto);
}
