package com.logistic.dto.Item.response;

import com.logistic.domain.util.Address;
import lombok.Data;

@Data
public class FindItemResDto {

    private String itemName;
    private Address origin;
    private int price;
    private int stockQuantity;

}
