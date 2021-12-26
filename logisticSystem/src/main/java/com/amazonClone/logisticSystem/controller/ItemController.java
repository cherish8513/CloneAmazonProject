package com.amazonClone.logisticSystem.controller;

import com.amazonClone.logisticSystem.constant.SessionConst;
import com.amazonClone.logisticSystem.dto.Item.request.SaveItemReqDto;
import com.amazonClone.logisticSystem.dto.Item.response.ItemResDto;
import com.amazonClone.logisticSystem.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
