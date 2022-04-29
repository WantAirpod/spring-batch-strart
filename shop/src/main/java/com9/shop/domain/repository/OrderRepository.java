package com9.shop.domain.repository;


import com9.shop.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Item, String>, OrderRepositoryCustom {
    Item findByItemId(Long itemId);
    List<Item> findAllByItemId(Long itemId);
}
