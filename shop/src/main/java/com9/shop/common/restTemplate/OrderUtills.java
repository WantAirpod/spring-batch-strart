package com9.shop.common.restTemplate;

import com9.shop.common.exception.SoldOutException;
import com9.shop.domain.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
public class OrderUtills {
    public ResponseEntity<Item> possibleOrderItemCheck(Long itemId) throws Exception {
        RestTemplate restTemplate =new RestTemplate();
        String BASE_URL = "http://localhost:8082/getItem";
        try {
            ResponseEntity<Item> forEntity = restTemplate.getForEntity(BASE_URL + "/{itemId}", Item.class, itemId);
            System.out.println(forEntity.toString());
            return forEntity;
        }catch(Exception e){
            throw new Exception("없는 상품입니다.");
        }
    }

    public ResponseEntity<String> orderTemplate(Long itemId, Long orderStock, Long nowStock){
        RestTemplate restTemplate =new RestTemplate();
        Item item = Item.builder()
                .itemId(itemId)
                .stock(orderStock)
                .build();
        //http://localhost:8082/orderItem?itemId=779943&stock=111
        String BASE_URL = "http://localhost:8082/orderItem";
        try {
            ResponseEntity<String> itemResponseEntity = restTemplate.postForEntity(BASE_URL, item, String.class);
            System.out.println(itemResponseEntity.toString());
            return itemResponseEntity;
        }catch (SoldOutException e){
            throw new SoldOutException("제고가 부족합니다.");
        }
    }
    public void itemListTemplate() throws Exception {
        RestTemplate restTemplate =new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", headers);
        String BASE_URL = "http://localhost:8082/list";
        URI url = URI.create(BASE_URL);
        try {
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println(response.getBody().toString());
        }catch(Exception e){
            throw new Exception("없는 상품입니다.");
        }
    }



}
