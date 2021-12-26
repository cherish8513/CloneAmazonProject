package com.amazonClone.logisticSystem.controller;

import com.amazonClone.logisticSystem.dto.order.request.SaveOrderReqDto;
import com.amazonClone.logisticSystem.dto.order.response.FindOrdersResDto;
import com.amazonClone.logisticSystem.service.order.OrderService;
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
    public ResponseEntity<Long> createOrder(@RequestBody SaveOrderReqDto reqDto){
        return new ResponseEntity(orderService.order(reqDto), HttpStatus.OK);
    }

    @DeleteMapping
    public HttpStatus cancelOrder(Long orderId){
        orderService.cancelOrder(orderId);
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<List<FindOrdersResDto>> findAllOrders(Long memberId){
        return new ResponseEntity(orderService.findOrders(memberId), HttpStatus.OK);
    }
}
