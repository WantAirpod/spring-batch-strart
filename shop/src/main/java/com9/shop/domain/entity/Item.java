package com9.shop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 상품번호,상품명,판매가격,재고수량 Entity
 */
@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private Long itemId;         /* 상품 번호 */
    private String name;        /* 상품명 */
    private Long price;         /* 판매가격 */
    private Long stock;         /* 재고수량 */

}
