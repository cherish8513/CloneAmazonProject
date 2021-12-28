package com.logistic.dto.member.request;

import com.logistic.domain.member.Member;
import com.logistic.domain.member.MemberRole;
import com.logistic.domain.util.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveMemberReqDto {
    private String name;
    private String email;
    private String password;
    private Address address;
    private MemberRole role;

    @Builder
    public SaveMemberReqDto(String name, String email, String password, Address address, MemberRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public Member toEntity(){
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .address(this.address)
                .role(role)
                .build();
    }
}
