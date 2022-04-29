package com9.shop;

import com9.shop.common.CsvParser;
import com9.shop.common.restTemplate.OrderUtills;
import com9.shop.domain.entity.dto.CalculateItem;
import com9.shop.run.RunProgram;
import com9.shop.run.checkInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

//@EnableBatchProcessing
@SpringBootApplication
@Slf4j
public class ShopApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ShopApplication.class, args);
		//orderUtills
		OrderUtills orderUtills = new OrderUtills();
		RunProgram runProgram = new RunProgram();
		//csv parse & insert db
		CsvParser csvParser = new CsvParser();
		csvParser.parser();
		CalculateItem calculateItem = new CalculateItem();
		
		//print
		System.out.println("입력(o[order]: 주문, q[quit]: 종료 : ");
		Scanner sc = new Scanner(System.in);
		String operation = sc.nextLine();
		//주문
		while (operation.equals("o")) {
			// o / q / 기타 판별

			checkInput checkInput = runProgram.programStart(operation);
				// 상품번호 입력
			System.out.print("상품번호 : ");
			String itemNumStr = sc.nextLine();
			//상품 미입력
			if(itemNumStr.equals(" ")){
				//계산
				if(calculateItem.getTotal()==0){
					System.out.println("주문이 없습니다.");
					break;
				}
				System.out.println("주문 내역:");
				System.out.println("----------------------------------------------------");
				for (String key : calculateItem.getItemInfo().keySet()) {
					System.out.println(key + " - " + calculateItem.getItemInfo().get(key) + "개");
				}
				System.out.println("----------------------------------------------------");
				System.out.println("주문금액 : " + calculateItem.getDeliveryFee() + "원");
				System.out.println("배송비 : " + calculateItem.getTotal() + "원");
			} else {
				//상품번호가 입력 되었을떄! 상품이 존재여부 파악
				if(runProgram.productProgram(itemNumStr)){
					//수량 입력
					System.out.print("수량 : ");
					String orderStockStr = sc.nextLine();
					calculateItem = runProgram.stockProgram(orderStockStr);
				}else{
					System.out.println("해당 상품이 없습니다.");
				}
			}

		}
	}
}
