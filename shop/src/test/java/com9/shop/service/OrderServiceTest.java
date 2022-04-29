package com9.shop.service;

import com9.shop.domain.entity.Item;
import com9.shop.domain.repository.OrderRepository;
import com9.shop.service.order.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    /**
     * http://localhost:8082/getItem?itemId=779943
     */
    @Test
    public void test(){
        RestTemplate restTemplate =new RestTemplate();
        //주문하기
        MultiValueMap<String, Long> params = new LinkedMultiValueMap<>();
        params.add("itemId", 779943L);
        Long temp = 779943L;
        //http://localhost:8082/orderItem?itemId=779943&stock=111
        String BASE_URL = "http://localhost:8082/getItem";
        //ResponseEntity<Item> forEntity = restTemplate.getForEntity(BASE_URL + "/{itemId}", Item.class, temp);
        Item body = restTemplate.getForEntity(BASE_URL + "/{itemId}", Item.class, temp).getBody();
        System.out.println(body.getItemId());
        System.out.println(body.getStock());
        System.out.println(body.getName());
        System.out.println(body.getPrice());


    }

    @Test
    public void test99zz() throws Exception {
        RestTemplate restTemplate =new RestTemplate();
        String BASE_URL = "http://localhost:8082/list";
        ResponseEntity<Item> forEntity = restTemplate.getForEntity(BASE_URL, Item.class);
        System.out.println(forEntity);
    }

    @Test
    public void testzz222() throws IOException {
        //Long stockBalance = item.getStock() - stock;
        RestTemplate restTemplate =new RestTemplate();
        Long stockBalance = 109L;
        Item item = Item.builder()
                .itemId(779943L)
                .stock(8888L)
                .build();

        //http://localhost:8082/orderItem?itemId=779943&stock=111
        String BASE_URL = "http://localhost:8082/orderItem";
        ResponseEntity<String> itemResponseEntity = restTemplate.postForEntity(BASE_URL, item, String.class);
        System.out.println(itemResponseEntity.toString());
    }

}


