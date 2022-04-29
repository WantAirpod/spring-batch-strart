package com9.shop.controller;

import com9.shop.domain.entity.Item;
import com9.shop.domain.entity.dto.OrderDTO;
import com9.shop.service.order.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ApiOperation(value = "전체 상품 조회", notes = "전체 상품 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<List<Item>> itemList (){
        List<Item> list = orderService.getItemList();
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "단일 조회", notes = "단일 상품 조회합니다.")
    @GetMapping("/getItem/{itemId}")
    public ResponseEntity<Item> itemListAll (@PathVariable Long itemId){
        return ResponseEntity.ok(orderService.getItem(itemId));
    }

    @ApiOperation(value = "상품 주문", notes = "상품 주문 합니다.")
    @GetMapping("/orderItem/{itemId}/{stock}")
    public ResponseEntity<Long> orderItem (@PathVariable Long itemId, @PathVariable Long stock){
        return ResponseEntity.ok(orderService.orderItem(itemId, stock));
    }

    @PostMapping("/orderItem")
    public ResponseEntity<Long> orderItem2 (@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.orderItem(orderDTO.getItemId(),orderDTO.getStock()));
    }
}
