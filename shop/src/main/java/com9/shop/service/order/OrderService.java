package com9.shop.service.order;

import com9.shop.common.exception.SoldOutException;
import com9.shop.domain.entity.Item;
import com9.shop.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Item> getItemList(){
        return orderRepository.findAll();
    }

    // 상품 존재 확인
    public Item getItem(Long itemId){
        return orderRepository.findByItemId(itemId);
    }

    // 주문
    public Long orderItem(Long itemId, Long stock) {
        // 상품재고확인
        Item item = orderRepository.findByItemId(itemId);
        Long operation = item.getStock() - stock;
        if(operation >= 0){
            return orderRepository.updateItemStock(itemId,operation);
        }else{
            System.out.println("재고부족");
            throw new SoldOutException("재고가 부족합니다.");
        }
    }
}
