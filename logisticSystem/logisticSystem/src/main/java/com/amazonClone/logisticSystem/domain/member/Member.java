package com.amazonClone.logisticSystem.domain.member;

import com.amazonClone.logisticSystem.domain.util.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private LocalDateTime joinDate;

    @Builder
    public Member(String name, String email, String password, Address address, MemberRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.joinDate = LocalDateTime.now();
    }

    public Member changePassword(String password){
        this.password = password;
        return this;
    }
}
