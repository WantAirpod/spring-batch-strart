package com9.shop.domain.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Long itemId;        /* 상품 번호 */
    private Long stock;     /* 재고수량 */
}
