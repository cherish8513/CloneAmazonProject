package com.amazonClone.logisticSystem.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeRequestDto {
    String email;
    String password;

    @Builder
    public ChangeRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
