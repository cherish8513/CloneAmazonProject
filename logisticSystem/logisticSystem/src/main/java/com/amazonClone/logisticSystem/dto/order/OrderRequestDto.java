package com.amazonClone.logisticSystem.dto.order;

import com.amazonClone.logisticSystem.domain.util.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequestDto {

    public Long memberId;
    public HashMap<Long, Integer> items;
    public List<Address> addresses;

    @Builder
    public OrderRequestDto(Long memberId, HashMap<Long, Integer> items, List<Address> addresses) {
        this.memberId = memberId;
        this.items = items;
        this.addresses = addresses;
    }
}
