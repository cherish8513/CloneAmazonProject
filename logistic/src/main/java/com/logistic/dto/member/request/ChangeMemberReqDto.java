package com.logistic.dto.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeMemberReqDto {
    String email;
    String password;

    @Builder
    public ChangeMemberReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
