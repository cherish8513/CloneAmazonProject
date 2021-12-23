package com.amazonClone.logisticSystem.service.item;

import com.amazonClone.logisticSystem.domain.categoryItem.CategoryItem;
import com.amazonClone.logisticSystem.domain.item.Item;
import com.amazonClone.logisticSystem.domain.member.Member;
import com.amazonClone.logisticSystem.domain.member.MemberRole;
import com.amazonClone.logisticSystem.dto.Item.ItemInfoRequestDto;
import com.amazonClone.logisticSystem.dto.Item.ItemInfoResponseDto;
import com.amazonClone.logisticSystem.repository.item.ItemRepository;
import com.amazonClone.logisticSystem.repository.member.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final JpaMemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Override
    public ItemInfoResponseDto registerItem(ItemInfoRequestDto reqDto, Long memberId) {

        // 멤버 계정 검증
        Optional<Member> byId = memberRepository.findById(memberId);
        Member seller = byId.orElseThrow(() -> new IllegalArgumentException("없는 회원 입니다"));

        // 생산자인지 검증
        if (!seller.getRole().equals(MemberRole.SELLER)) {
            throw new IllegalArgumentException("생산자 계정만이 물건을 등록할 수 있습니다.");
        }

        // dto -> entity 변환
        Item item = Item.builder()
                .name(reqDto.getName())
                .price(reqDto.getPrice())
                .stockQuantity(reqDto.getStockQuantity())
                .origin(reqDto.getOrigin())
                .seller(seller)
                .build();

        for (CategoryItem category : reqDto.getCategories()) {
            item.getCategories().add(category);
        }

        // Repository로 전송
        Long savedItemId = itemRepository.save(item);

        // DB에 저장된 item 엔티티 -> 저장된 정보를 View에 전달하기 위한 정보
        Item savedItem = itemRepository.findOne(savedItemId);

        // domain -> dto 변환
        ItemInfoResponseDto resDto = transformDomain(savedItem);

        return resDto;
    }

    @Override
    public ItemInfoResponseDto findItem(Long itemId) {
        // Repository로 id 전송
        Item findItem = itemRepository.findOne(itemId);

        // domain -> dto 변환
        ItemInfoResponseDto resDto = transformDomain(findItem);

        return resDto;
    }

    @Override
    public List<ItemInfoResponseDto> findItemList(Long memberId) {
        return null;
    }

    @Override
    public void updateItem(Long itemId, Long memberId, ItemInfoRequestDto reqDto) {

        Item findItem = itemRepository.findOne(itemId);

        if (!findItem.getSeller().getId().equals(memberId)) {
            throw new IllegalArgumentException("수정이 허용되지 않는 멤버입니다.");
        }

        findItem.updateItem(reqDto.getName(), reqDto.getOrigin(), reqDto.getPrice(), reqDto.getStockQuantity());
    }

    @Override
    public void deleteItem(Long itemId, Long memberId) {
        Item findItem = itemRepository.findOne(itemId);

        if (!findItem.getSeller().getId().equals(memberId)) {
            throw new IllegalArgumentException("삭제가 허용되지 않는 멤버입니다.");
        }

        itemRepository.delete(findItem);
    }

    /**
     * 도메인을 DTO로 변환시키는 메소드
     */
    private ItemInfoResponseDto transformDomain(Item savedItem) {
        ItemInfoResponseDto resDto = new ItemInfoResponseDto();

        resDto.setId(savedItem.getId());
        resDto.setName(savedItem.getName());
        resDto.setPrice(savedItem.getPrice());
        resDto.setStockQuantity(savedItem.getStockQuantity());
        resDto.setOrigin(savedItem.getOrigin());
        resDto.setCategories(savedItem.getCategories());
        return resDto;
    }
}
