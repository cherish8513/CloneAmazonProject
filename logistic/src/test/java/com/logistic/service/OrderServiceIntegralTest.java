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
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
//        createMember("a@a");
//        createMember("b@b");
//        createItem("사과");
//        createItem("배");
//        createItem("귤");
//        createItem("가지");
//
//        HashMap<Long, Integer> items = new HashMap<>();
//        items.put(1L, 30);
//        items.put(2L, 10);
//
//        HashMap<Long, Integer> items2 = new HashMap<>();
//        items2.put(3L, 30);
//        items2.put(4L, 10);
//
//        HashMap<Long, Integer> items3 = new HashMap<>();
//        items3.put(1L, 30);
//        items3.put(2L, 10);
//
//
//        Address address = new Address("서울", "2", "3");
//        Address address2 = new Address("부산", "2", "3");
//
//        SaveOrderReqDto requestDto = SaveOrderReqDto.builder() // a@a
//                .addresses(address)
//                .items(items)
//                .memberId(1L)
//                .build();
//
//        SaveOrderReqDto requestDto2 = SaveOrderReqDto.builder() // b@b
//                .addresses(address2)
//                .items(items2)
//                .memberId(2L)
//                .build();
//
//        SaveOrderReqDto requestDto3 = SaveOrderReqDto.builder() // a@a
//                .addresses(address)
//                .items(items3)
//                .memberId(1L)
//                .build();
//
//        List<SaveOrderReqDto> listRequestDtoA = new ArrayList<>();
//        List<SaveOrderReqDto> listRequestDtoB = new ArrayList<>();
//        listRequestDtoA.add(requestDto);
//        listRequestDtoA.add(requestDto3);
//        listRequestDtoB.add(requestDto2);
//
//        //when
//        orderService.order(listRequestDtoA);
//        orderService.order(listRequestDtoB);
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

    /**
     * a@a의 이메일 회원은 사과, 배를 서울에 1개, 부산에 1개 보낸다.
     */
    @Test
    public void 여러개의_배송지() throws Exception{
        //given

        //when
        List<FindOrdersResDto> findOrders = orderService.findOrders(1L);

        //then
        Assertions.assertThat(findOrders.get(0).getOrderItemName()).contains("사과", "배");
        Assertions.assertThat(findOrders.get(0).getAddresses().getCity().equals("서울"));
        Assertions.assertThat(findOrders.get(1).getOrderItemName()).contains("사과", "배");
        Assertions.assertThat(findOrders.get(1).getAddresses().getCity().equals("부산"));
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
