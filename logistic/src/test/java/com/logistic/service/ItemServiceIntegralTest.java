package com.logistic.service;

import com.logistic.domain.member.MemberRole;
import com.logistic.domain.util.Address;
import com.logistic.dto.Item.request.SaveItemReqDto;
import com.logistic.dto.Item.response.ItemResDto;
import com.logistic.dto.member.request.SaveMemberReqDto;
import com.logistic.service.item.ItemService;
import com.logistic.service.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@SpringBootTest
@Transactional
public class ItemServiceIntegralTest {

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @BeforeEach
    public void 회원가입() {
        SaveMemberReqDto requestDto = SaveMemberReqDto.builder()
                .name("이명범")
                .email("mungmnb777@gmail.com")
                .password("password")
                .address(new Address())
                .role(MemberRole.PRODUCER)
                .build();

        memberService.join(requestDto);
    }

    @Test
    public void 물건_등록() throws Exception {
        // given
        SaveItemReqDto reqDto = new SaveItemReqDto();
        reqDto.setName("사과");
        reqDto.setOrigin(new Address());
        reqDto.setPrice(5000);
        reqDto.setStockQuantity(20);
        reqDto.setCategories(new ArrayList<>());

        // when
        ItemResDto resDto = itemService.registerItem(reqDto, 1L);

        // then
        Assertions.assertThat(resDto.getName()).isEqualTo("사과");
    }
}
