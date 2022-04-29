package com9.shop.domain.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CalculateItem {

    //상품명, 갯수
    private Map<String,Long> itemInfo;
    //배송비
    private Long deliveryFee;
    //주문금액
    private Long total;

}
