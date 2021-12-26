package com.amazonClone.logisticSystem.controller;

import com.amazonClone.logisticSystem.dto.member.request.ChangeMemberReqDto;
import com.amazonClone.logisticSystem.dto.member.request.SaveMemberReqDto;
import com.amazonClone.logisticSystem.service.member.MemberService;
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
    public ResponseEntity<Long> join(@RequestBody SaveMemberReqDto reqDto){
        return new ResponseEntity(memberService.join(reqDto), HttpStatus.OK);
    }

    @PutMapping("/changePw")
    public ResponseEntity<Long> changePassword(@RequestBody ChangeMemberReqDto reqDto){
        return new ResponseEntity(memberService.changePassword(reqDto), HttpStatus.OK);
    }
}
