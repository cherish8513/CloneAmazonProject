package com.logistic.service;


import com.logistic.domain.item.Item;
import com.logistic.domain.member.MemberRole;
import com.logistic.domain.util.Address;
import com.logistic.dto.member.request.SaveMemberReqDto;
import com.logistic.dto.order.request.SaveOrderReqDto;
import com.logistic.dto.order.response.FindOrdersResDto;
import com.logistic.repository.item.JpaItemRepository;
import com.logistic.service.member.MemberService;
import com.logistic.service.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional
@SpringBootTest
public class OrderServiceIntegralTest {

    @Autowired
    MemberService memberService;

    @Autowired
    OrderService orderService;

    @Autowired
    JpaItemRepository itemRepository;

    @BeforeEach
    public void init(){
        createMember("a@a");
        createMember("b@b");
        createItem("사과");
        createItem("배");
        createItem("귤");
        createItem("가지");
        HashMap<Long, Integer> items = new HashMap<>();
        items.put(1L, 30);
        items.put(2L, 10);

        HashMap<Long, Integer> items2 = new HashMap<>();
        items.put(3L, 30);
        items.put(4L, 10);

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("1", "2", "3"));
        addresses.add(new Address("4", "5", "6"));

        SaveOrderReqDto requestDto = SaveOrderReqDto.builder() // a@a
                .addresses(addresses)
                .items(items)
                .memberId(1L)
                .build();

        SaveOrderReqDto requestDto2 = SaveOrderReqDto.builder() // b@b
                .addresses(addresses)
                .items(items2)
                .memberId(2L)
                .build();

        orderService.order(requestDto);
        orderService.order(requestDto2);
    }

    /*
    a@a의 이메일 회원은 사과, 배를 주문 한 상태
    b@b의 이메일 회원은 귤, 가지를 주문 한 상태
     */
    @Test
    public void 회원_주문_조회() throws Exception{
        //given

        //when
        List<FindOrdersResDto> findOrders = orderService.findOrders(1L);

        //then
        Assertions.assertThat(findOrders.get(0).getOrderItemName()).contains("사과", "배");
    }

    private void createItem(String name) {
        itemRepository.save(Item.builder()
            .name(name)
            .origin(null)
            .price(1000)
            .stockQuantity(100)
            .build());
    }

    private void createMember(String email) {
        SaveMemberReqDto requestDto = SaveMemberReqDto.builder()
                .name("이명범")
                .email(email)
                .password("password")
                .address(new Address())
                .role(MemberRole.PRODUCER)
                .build();
        memberService.join(requestDto);
    }
}
