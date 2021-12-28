package com.logistic.controller;

import com.logistic.constant.SessionConst;
import com.logistic.dto.Item.request.SaveItemReqDto;
import com.logistic.dto.Item.response.ItemResDto;
import com.logistic.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/register")
    public ResponseEntity<ItemResDto> registerItem(@RequestBody SaveItemReqDto reqDto,
                                                   HttpSession session) {
        Long currentLoginId = (Long) session.getAttribute(SessionConst.LOGIN_ID);

        ItemResDto resDto = itemService.registerItem(reqDto, currentLoginId);

        return new ResponseEntity(resDto, HttpStatus.OK);
    }
}
