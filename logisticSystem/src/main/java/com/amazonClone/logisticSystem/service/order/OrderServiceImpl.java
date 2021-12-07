package com.amazonClone.logisticSystem.service.order;

import com.amazonClone.logisticSystem.domain.delivery.Delivery;
import com.amazonClone.logisticSystem.domain.item.Item;
import com.amazonClone.logisticSystem.domain.member.Member;
import com.amazonClone.logisticSystem.domain.order.Order;
import com.amazonClone.logisticSystem.domain.orderItem.OrderItem;
import com.amazonClone.logisticSystem.dto.order.OrderRequestDto;
import com.amazonClone.logisticSystem.repository.item.JpaItemRepository;
import com.amazonClone.logisticSystem.repository.member.JpaMemberRepository;
import com.amazonClone.logisticSystem.repository.order.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final JpaMemberRepository memberRepository;
    private final JpaOrderRepository orderRepository;
    private final JpaItemRepository itemRepository;

    @Override
    @Transactional
    public Long order(OrderRequestDto requestDto) {
        //회원 조회
        Member member = getMember(memberRepository.findById(requestDto.getMemberId()));

        //주문 상품 생성
        List<OrderItem> orderItems = new ArrayList<>();

        HashMap<Long, Integer> items = requestDto.getItems();
        for (Long itemId:items.keySet()) {
            Item item = getItem(itemRepository.findById(itemId));
            int count = items.get(itemId);
            orderItems.add(OrderItem.builder()
                    .item(item)
                    .count(count)
                    .build());
        }

        //배송지 생성
        List<Delivery> deliveries = new ArrayList<>();
        requestDto.getAddresses().stream().forEach(address -> deliveries.add(new Delivery(address)));

        //주문 생성
        Order order = Order.builder()
                .member(member)
                .orderItems(orderItems)
                .deliveries(deliveries)
                .build();

        //주문 저장
        Order saveOrder = orderRepository.save(order);

        return saveOrder.getId();
    }

    //유효한 회원인지 검증
    private Member getMember(Optional<Member> byId) {
        return byId.orElseThrow(() -> new IllegalArgumentException("없는 회원 입니다"));
    }

    //유효한 상품인지 검증
    private Item getItem(Optional<Item> byId) {
        return byId.orElseThrow(() -> new IllegalArgumentException("없는 상품 입니다"));
    }

}
