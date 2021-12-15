package com.amazonClone.logisticSystem.dto.Item;

import com.amazonClone.logisticSystem.domain.util.Address;
import lombok.Data;

@Data
public class FindItemResDto {

    private String itemName;
    private Address origin;
    private int price;
    private int stockQuantity;

}
