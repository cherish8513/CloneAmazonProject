package com.logistic.controller;

import com.logistic.dto.member.request.ChangeMemberReqDto;
import com.logistic.dto.member.request.SaveMemberReqDto;
import com.logistic.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public Long join(@RequestBody SaveMemberReqDto reqDto){
        return memberService.join(reqDto);
    }

    @PutMapping("/changePw")
    public Long changePassword(@RequestBody ChangeMemberReqDto reqDto){
        return memberService.changePassword(reqDto);
    }
}
