package com.amazonClone.logisticSystem.service;

import com.amazonClone.logisticSystem.domain.item.Item;
import com.amazonClone.logisticSystem.domain.member.Member;
import com.amazonClone.logisticSystem.domain.order.Order;
import com.amazonClone.logisticSystem.domain.util.Address;
import com.amazonClone.logisticSystem.dto.order.request.SaveOrderRequestDto;
import com.amazonClone.logisticSystem.repository.item.JpaItemRepository;
import com.amazonClone.logisticSystem.repository.member.JpaMemberRepository;
import com.amazonClone.logisticSystem.repository.order.JpaOrderRepository;
import com.amazonClone.logisticSystem.service.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {

    @Mock
    private JpaMemberRepository memberRepository;
    @Mock
    private JpaItemRepository itemRepository;
    @Mock
    private JpaOrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;


    @Test
    public void 주문_생성_테스트 () throws Exception{
        //given
        Member member = Member.builder().name("kim").password("1").build();

        Item item1 = Item.builder()
                .name("사과")
                .origin(null)
                .price(1000)
                .stockQuantity(100)
                .build();

        Item item2 = Item.builder()
                .name("배")
                .origin(null)
                .price(2000)
                .stockQuantity(200)
                .build();
        HashMap<Long, Integer> items = new HashMap<>();
        items.put(1L, 30);
        items.put(2L, 10);

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("1", "2", "3"));
        addresses.add(new Address("4", "5", "6"));

        SaveOrderRequestDto requestDto = SaveOrderRequestDto.builder()
                .addresses(addresses)
                .items(items)
                .memberId(1L)
                .build();

        when(memberRepository.findById(requestDto.getMemberId())).thenReturn(Optional.of(member));
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item1));
        when(itemRepository.findById(2L)).thenReturn(Optional.of(item2));
        when(orderRepository.save(any(Order.class))).then(AdditionalAnswers.returnsFirstArg());

        //when
        Long orderId = orderService.order(requestDto);

        //then
        verify(memberRepository, times(1)).findById(any(Long.class));
        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).findById(2L);
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
