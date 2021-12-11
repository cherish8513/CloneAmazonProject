package com.amazonClone.logisticSystem.service.member;

import com.amazonClone.logisticSystem.dto.member.request.ChangeRequestDto;
import com.amazonClone.logisticSystem.dto.member.request.SaveRequestDto;

public interface MemberService {
    public Long join(SaveRequestDto requestDto);
    public Long changePassword(ChangeRequestDto requestDto);
}
