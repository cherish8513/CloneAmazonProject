package com.logistic.service.item;

import com.logistic.dto.Item.request.SaveItemReqDto;
import com.logistic.dto.Item.response.ItemResDto;

import java.util.List;

public interface ItemService {
    /**
     * 아이템 등록
     */
    ItemResDto registerItem(SaveItemReqDto dto, Long memberId);

    /**
     * 등록한 아이템 하나 조회
     */
    ItemResDto findItem(Long itemId);

    /**
     * 등록한 아이템 리스트 조회
     */
    List<ItemResDto> findItemList(Long memberId);

    /**
     * 등록한 아이템 수정
     */
    void updateItem(Long itemId, Long memberId, SaveItemReqDto dto);

    /**
     * 등록한 아이템 삭제
     */
    void deleteItem(Long itemId, Long memberId);
}
