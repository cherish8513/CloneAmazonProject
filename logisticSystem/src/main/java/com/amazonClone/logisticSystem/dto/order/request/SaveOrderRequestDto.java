package com.amazonClone.logisticSystem.dto.order.request;

import com.amazonClone.logisticSystem.domain.util.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class SaveOrderRequestDto {

    public Long memberId;
    public HashMap<Long, Integer> items;
    public List<Address> addresses;

    @Builder
    public SaveOrderRequestDto(Long memberId, HashMap<Long, Integer> items, List<Address> addresses) {
        this.memberId = memberId;
        this.items = items;
        this.addresses = addresses;
    }
}
