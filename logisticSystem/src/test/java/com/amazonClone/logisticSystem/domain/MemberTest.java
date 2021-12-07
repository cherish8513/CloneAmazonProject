package com.amazonClone.logisticSystem.domain;

import com.amazonClone.logisticSystem.domain.member.MemberRole;
import com.amazonClone.logisticSystem.domain.util.Address;
import com.amazonClone.logisticSystem.dto.member.SaveRequestDto;
import com.amazonClone.logisticSystem.repository.member.JpaMemberRepository;
import com.amazonClone.logisticSystem.service.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class MemberTest {

    @Mock
    private JpaMemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    public void 멤버_중복_테스트() throws Exception{
        //given
        SaveRequestDto requestDto = SaveRequestDto.builder()
                .name("이명범")
                .email("email")
                .password("password")
                .address(new Address())
                .role(MemberRole.PRODUCER)
                .build();


        doReturn(Optional.of(requestDto.toEntity())).when(memberRepository).findByEmail(requestDto.getEmail());

        //when
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(requestDto);
        });

        //then
        Assertions.assertEquals("이미 존재하는 이메일 입니다.", thrown.getMessage());
    }

}
