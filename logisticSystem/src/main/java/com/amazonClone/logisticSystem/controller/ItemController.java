package com.amazonClone.logisticSystem.controller;

import com.amazonClone.logisticSystem.constant.SessionConst;
import com.amazonClone.logisticSystem.dto.Item.ItemInfoRequestDto;
import com.amazonClone.logisticSystem.dto.Item.ItemInfoResponseDto;
import com.amazonClone.logisticSystem.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/register")
    public ResponseEntity<ItemInfoResponseDto> registerItem(@RequestBody ItemInfoRequestDto reqDto,
                                                            HttpSession session) {
        Long currentLoginId = (Long) session.getAttribute(SessionConst.LOGIN_ID);

        ItemInfoResponseDto resDto = itemService.registerItem(reqDto, currentLoginId);

        return new ResponseEntity(resDto, HttpStatus.OK);
    }
}
