package com.amazonClone.logisticSystem.service;

import com.amazonClone.logisticSystem.domain.member.MemberRole;
import com.amazonClone.logisticSystem.domain.util.Address;
import com.amazonClone.logisticSystem.dto.Item.ItemInfoRequestDto;
import com.amazonClone.logisticSystem.dto.Item.ItemInfoResponseDto;
import com.amazonClone.logisticSystem.dto.member.request.SaveReqDto;
import com.amazonClone.logisticSystem.service.item.ItemService;
import com.amazonClone.logisticSystem.service.member.MemberService;
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
        SaveReqDto requestDto = SaveReqDto.builder()
                .name("이명범")
                .email("mungmnb777@gmail.com")
                .password("password")
                .address(new Address())
                .role(MemberRole.SELLER)
                .build();

        memberService.join(requestDto);
    }

    @Test
    public void 물건_등록() throws Exception {

        // given
        ItemInfoRequestDto reqDto = new ItemInfoRequestDto();
        reqDto.setName("사과");
        reqDto.setOrigin(new Address());
        reqDto.setPrice(5000);
        reqDto.setStockQuantity(20);
        reqDto.setCategories(new ArrayList<>());

        // when
        ItemInfoResponseDto resDto = itemService.registerItem(reqDto, 1L);

        // then
        Assertions.assertThat(resDto.getName()).isEqualTo("사과");
    }
}
