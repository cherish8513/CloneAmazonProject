package com.amazonClone.logisticSystem.service.item;

import com.amazonClone.logisticSystem.domain.item.Item;
import com.amazonClone.logisticSystem.dto.Item.ItemInfoRequestDto;
import com.amazonClone.logisticSystem.dto.Item.ItemInfoResponseDto;

import java.util.List;

public interface ItemService {
    /**
     * 아이템 등록
     */
    ItemInfoResponseDto registerItem(ItemInfoRequestDto dto, Long memberId);

    /**
     * 등록한 아이템 하나 조회
     */
    ItemInfoResponseDto findItem(Long itemId);

    /**
     * 등록한 아이템 리스트 조회
     */
    List<ItemInfoResponseDto> findItemList(Long memberId);

    /**
     * 등록한 아이템 수정
     */
    void updateItem(Long itemId, Long memberId, ItemInfoRequestDto dto);

    /**
     * 등록한 아이템 삭제
     */
    void deleteItem(Long itemId, Long memberId);
}
