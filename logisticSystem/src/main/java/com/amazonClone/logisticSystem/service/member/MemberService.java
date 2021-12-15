package com.amazonClone.logisticSystem.service.member;

import com.amazonClone.logisticSystem.dto.member.request.ChangeReqDto;
import com.amazonClone.logisticSystem.dto.member.request.SaveReqDto;

public interface MemberService {
    public Long join(SaveReqDto requestDto);
    public Long changePassword(ChangeReqDto requestDto);
}
