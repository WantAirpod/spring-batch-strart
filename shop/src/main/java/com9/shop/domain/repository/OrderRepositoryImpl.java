package com9.shop.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com9.shop.domain.entity.QItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QItem qItem = QItem.item;

    @Override
    @Transactional
    public Long updateItemStock(Long itemId, Long stock) {
        return jpaQueryFactory
                .update(qItem)
                .set(qItem.stock,stock)
                .where(qItem.itemId.eq(itemId))
                .execute();
    }
}
