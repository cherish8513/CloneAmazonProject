package com.amazonClone.logisticSystem.service.member;

import com.amazonClone.logisticSystem.domain.member.Member;
import com.amazonClone.logisticSystem.dto.member.ChangeRequestDto;
import com.amazonClone.logisticSystem.dto.member.SaveRequestDto;

import java.util.Optional;

public interface MemberService {
    public Long join(SaveRequestDto requestDto);
    public Long changePassword(ChangeRequestDto requestDto);
}
