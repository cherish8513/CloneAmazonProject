package com.logistic.service.order;

import com.logistic.aop.annotation.Retry;
import com.logistic.domain.delivery.Delivery;
import com.logistic.domain.item.Item;
import com.logistic.domain.member.Member;
import com.logistic.domain.order.Order;
import com.logistic.domain.orderItem.OrderItem;
import com.logistic.domain.util.Address;
import com.logistic.dto.order.request.SaveOrderReqDto;
import com.logistic.dto.order.response.FindOrdersResDto;
import com.logistic.repository.item.JpaItemRepository;
import com.logistic.repository.member.JpaMemberRepository;
import com.logistic.repository.order.JpaOrderRepository;
import com.logistic.repository.order.query.OrderQueryRepository;
import com.logistic.service.util.ValidationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final JpaMemberRepository memberRepository;
    private final JpaOrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final JpaItemRepository itemRepository;
    private final ValidationCheck validationCheck;

    @Override
    @Transactional
    public void order(List<SaveOrderReqDto> requestDto) {
        //회원 조회
        Member member = validationCheck.getMember(memberRepository.findById(requestDto.get(0).getMemberId()));

        for (SaveOrderReqDto reqDto :requestDto) {

            //주문 생성
            Order order = new Order(member);

            //주문 상품 생성
            List<OrderItem> orderItems = new ArrayList<>();

            HashMap<Long, Integer> items = reqDto.getItems();
            if (items != null) {
                for (Long itemId : items.keySet()) {
                    Item item = validationCheck.getItem(itemRepository.findById(itemId));
                    int count = items.get(itemId);
                    item.removeStock(count);
                    orderItems.add(OrderItem.builder()
                            .order(order)
                            .item(item)
                            .count(count)
                            .build());
                }
            }

            //배송지 생성
            Delivery.builder()
                    .order(order)
                    .address(reqDto.getAddresses())
                    .build();


            //주문 저장
            Order saveOrder = orderRepository.save(order);

        }
    }
  
    @Override
    public void cancelOrder(Long orderId) {
        Order order = validationCheck.getOrder(orderRepository.findById(orderId));
        order.cancel();
    }

    @Retry
    @Override
    public List<FindOrdersResDto> findOrders(Long memberId) {
        List<Order> findOrders = orderQueryRepository.findAllOrdersById(memberId);
        List<FindOrdersResDto> resDtoList = new ArrayList<>();
        for (Order findOrder : findOrders) {
            resDtoList.add(new FindOrdersResDto(findOrder));
        }

        return resDtoList;
    }
}
