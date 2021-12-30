package com.logistic.controller;

import com.logistic.dto.order.request.SaveOrderReqDto;
import com.logistic.dto.order.response.FindOrdersResDto;
import com.logistic.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody List<SaveOrderReqDto> reqDto){
        orderService.order(reqDto);
    }

    @DeleteMapping
    public void cancelOrder(Long orderId){
        orderService.cancelOrder(orderId);
    }

    @GetMapping
    public List<FindOrdersResDto> findAllOrders(Long memberId){
        return orderService.findOrders(memberId);
    }
}
