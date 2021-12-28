package com.logistic.service.item;

import com.logistic.domain.categoryItem.CategoryItem;
import com.logistic.domain.item.Item;
import com.logistic.domain.member.Member;
import com.logistic.domain.member.MemberRole;
import com.logistic.dto.Item.request.SaveItemReqDto;
import com.logistic.dto.Item.response.ItemResDto;
import com.logistic.repository.item.JpaItemRepository;
import com.logistic.repository.member.JpaMemberRepository;
import com.logistic.service.util.ValidationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final JpaMemberRepository memberRepository;
    private final JpaItemRepository itemRepository;
    private final ValidationCheck validationCheck;

    @Override
    public ItemResDto registerItem(SaveItemReqDto reqDto, Long memberId) {

        Member producer = validationCheck.getMember(memberRepository.findById(memberId));

        // 생산자인지 검증
        if (!producer.getRole().equals(MemberRole.PRODUCER)) {
            throw new IllegalArgumentException("생산자 계정만이 물건을 등록할 수 있습니다.");
        }

        // dto -> entity 변환
        Item item = Item.builder()
                .name(reqDto.getName())
                .price(reqDto.getPrice())
                .stockQuantity(reqDto.getStockQuantity())
                .origin(reqDto.getOrigin())
                .seller(producer)
                .build();

        for (CategoryItem category : reqDto.getCategories()) {
            item.getCategories().add(category);
        }

        // Repository로 전송
        Long savedItemId = itemRepository.save(item).getId();

        // DB에 저장된 item 엔티티 -> 저장된 정보를 View에 전달하기 위한 정보
        Item savedItem = validationCheck.getItem(itemRepository.findById(savedItemId));

        // domain -> dto 변환
        ItemResDto resDto = transformDomain(savedItem);

        return resDto;
    }

    @Override
    public ItemResDto findItem(Long itemId) {
        // Repository로 id 전송
        Item findItem = validationCheck.getItem(itemRepository.findById(itemId));

        // domain -> dto 변환
        ItemResDto resDto = transformDomain(findItem);

        return resDto;
    }

    @Override
    public List<ItemResDto> findItemList(Long memberId) {
        return null;
    }

    @Override
    public void updateItem(Long itemId, Long memberId, SaveItemReqDto reqDto) {

        Item findItem = validationCheck.getItem(itemRepository.findById(itemId));

        if (!findItem.getSeller().getId().equals(memberId)) {
            throw new IllegalArgumentException("수정이 허용되지 않는 멤버입니다.");
        }

        findItem.updateItem(reqDto.getName(), reqDto.getOrigin(), reqDto.getPrice(), reqDto.getStockQuantity());
    }

    @Override
    public void deleteItem(Long itemId, Long memberId) {
        Item findItem = validationCheck.getItem(itemRepository.findById(itemId));

        if (!findItem.getSeller().getId().equals(memberId)) {
            throw new IllegalArgumentException("삭제가 허용되지 않는 멤버입니다.");
        }

        itemRepository.delete(findItem);
    }

    /**
     * 도메인을 DTO로 변환시키는 메소드
     */
    private ItemResDto transformDomain(Item savedItem) {
        ItemResDto resDto = new ItemResDto();

        resDto.setId(savedItem.getId());
        resDto.setName(savedItem.getName());
        resDto.setPrice(savedItem.getPrice());
        resDto.setStockQuantity(savedItem.getStockQuantity());
        resDto.setOrigin(savedItem.getOrigin());
        resDto.setCategories(savedItem.getCategories());
        return resDto;
    }

}
