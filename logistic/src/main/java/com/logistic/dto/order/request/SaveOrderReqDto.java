package com.logistic.dto.order.request;

import com.logistic.domain.util.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class SaveOrderReqDto {

    public Long memberId;
    public HashMap<Long, Integer> items;
    public List<Address> addresses;

    @Builder
    public SaveOrderReqDto(Long memberId, HashMap<Long, Integer> items, List<Address> addresses) {
        this.memberId = memberId;
        this.items = items;
        this.addresses = addresses;
    }
}
