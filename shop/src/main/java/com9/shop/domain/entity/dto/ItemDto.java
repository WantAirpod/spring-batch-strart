package com9.shop.domain.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long itemId;        /* 상품 번호 */
    private String name;    /* 상품명 */
    private Long price;     /* 판매가격 */
    private Long stock;     /* 재고수량 */
}
