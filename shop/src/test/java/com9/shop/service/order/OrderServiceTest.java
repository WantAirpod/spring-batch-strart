package com9.shop.service.order;

import com9.shop.domain.entity.Item;
import com9.shop.domain.repository.OrderRepository;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {
    //private Logger log = (Logger) LoggerFactory.getLogger(OrderServiceTest.class);

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void test() throws IOException, ParseException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String BASE_URL = "http://localhost:8082/list";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", headers);
        URI url = URI.create(BASE_URL);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody().toString());

    }

    RestTemplate restTemplate =new RestTemplate();
    Long stockBalance = 109L;
    Item item = Item.builder()
            .itemId(779943L)
            .stock(8888L)
            .build();

    //http://localhost:8082/orderItem?itemId=779943&stock=111




    @Test
    public void testThred() {
        RestTemplate restTemplate =new RestTemplate();
        String BASE_URL = "http://localhost:8082/orderItem";
        for(int i = 0  ; i < 5 ; i++) {

            //log.info("발사!");
            System.out.println("발사!");
            Item item = Item.builder()
                    .itemId(782858L)
                    .stock(6L)
                    .build();
            ResponseEntity<String> itemResponseEntity = restTemplate.postForEntity(BASE_URL, item, String.class);
            if (itemResponseEntity.getBody().equals("1")) {
                System.out.println("완료");
            }
            //Item item2 = orderRepository.findByItemId(782858L);
            //System.out.println(item2.getStock());
        }

    }


}