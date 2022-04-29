package com9.shop.run;

import com9.shop.common.restTemplate.OrderUtills;
import com9.shop.domain.entity.Item;
import com9.shop.domain.entity.dto.CalculateItem;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class RunProgram {
    private static OrderUtills orderUtills;
    private static CalculateItem calculateItem;
    private static Long itemId;
    private static Long nowStock;
    //map
    private static String orderItemName;
    private static Long ItemOrderStock;
    //totalCount
    private static Long totalCount = 0L;
    private static LinkedHashMap<String, Long> orderItem;
    /**
     * 주문(o) / 종료(q) / 오입력을 판별한다.
     * @param operation
     * @return
     * @throws Exception
     */
    public checkInput programStart(String operation) throws Exception {
        orderUtills = new OrderUtills();
        if(operation.equals("o")){
            //전체 목록 보여주기
            orderUtills.itemListTemplate();
            //상품번호입력하기(무한)->space바 일때가지
            return checkInput.O_INPUT;
            //재고수량 입력하기 ->space바 일때까지
        }else if(operation.equals("q")){
            //종료하기
            System.out.println("종료합니다.");
            return checkInput.P_INPUT;
        }else{
            //재입력하기
            System.out.println("다시 입력하세요.");
            return checkInput.OTHERS;
        }
    }

    /**
     * 상품 존재 여부를 판별한다.
     * @return
     * @throws Exception
     */
    public boolean productProgram(String productNumStr) throws Exception {
        orderUtills = new OrderUtills();
        //띄어쓰기 판별
        Long productNum = Long.parseLong(productNumStr);
        //상품 검색
        Item itemInfo = orderUtills.possibleOrderItemCheck(productNum).getBody();
        itemId = itemInfo.getItemId();
        nowStock = itemInfo.getStock();
        totalCount += itemInfo.getPrice();
        orderItemName = itemInfo.getName();
        //있는 상품
        if(itemInfo.getItemId() == null){
            return false;
        }else{
            return true;
        }
    }



    /**
     * 주문하기
     * @param orderStockStr
     * @return
     */
    public CalculateItem stockProgram(String orderStockStr){
        CalculateItem calculateItem = new CalculateItem();
        orderUtills = new OrderUtills();
        //계산하기
        Long orderStock = Long.parseLong(orderStockStr);
        ItemOrderStock = orderStock;
        ResponseEntity<String> responseEntity = orderUtills.orderTemplate(itemId, orderStock, nowStock);
        HashMap<String, Long> tempHashMap = new HashMap<>();
        tempHashMap.put(orderItemName,ItemOrderStock);
        calculateItem.setItemInfo(tempHashMap);
        calculateItem.setTotal(totalCount);
        return calculateItem;
    }

    /**
     * 계산 및 아이템
     * @return
     */
    public CalculateItem calculateProgram(){
        System.out.println(orderItemName + " "  + ItemOrderStock + " " + itemId + " " + totalCount);
        System.out.println("들어옴");
        orderItem.put(orderItemName,ItemOrderStock);
        //상품명, 갯수 입력
        calculateItem.setItemInfo(orderItem);
        //배송비 입력
        if(totalCount <= 50000){
            calculateItem.setDeliveryFee(2500L);
            totalCount += 2500L;
            //최종 주문 금액 입력
        }
        // 최종 금액 입력
        calculateItem.setTotal(totalCount);
        return calculateItem;
    }
}
