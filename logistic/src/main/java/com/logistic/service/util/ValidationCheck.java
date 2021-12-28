package com.logistic.service.util;

import com.logistic.domain.item.Item;
import com.logistic.domain.member.Member;
import com.logistic.domain.order.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationCheck {

    //유효한 회원인지 검증
    public Member getMember(Optional<Member> member) {
        return member.orElseThrow(() -> new IllegalArgumentException("없는 회원 입니다"));
    }

    // 중복 회원 검사
    public void validateDuplicate(Optional<Member> member){
        member.ifPresent(exist -> {
                    throw new IllegalStateException("이미 존재하는 이메일 입니다.");
                });
    }

    //유효한 상품인지 검증
    public Item getItem(Optional<Item> item) {
        return item.orElseThrow(() -> new IllegalArgumentException("없는 상품 입니다"));
    }

    //유효한 주문인지 검증
    public Order getOrder(Optional<Order> order){
        return order.orElseThrow(() -> new IllegalArgumentException("없는 주문 입니다"));
    }

}
