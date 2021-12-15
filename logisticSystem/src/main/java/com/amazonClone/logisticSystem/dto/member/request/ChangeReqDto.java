package com.amazonClone.logisticSystem.dto.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeReqDto {
    String email;
    String password;

    @Builder
    public ChangeReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
