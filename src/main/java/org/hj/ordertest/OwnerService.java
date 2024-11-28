package org.hj.ordertest;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final SimpMessagingTemplate messagingTemplate;

    @Async
    public void processOrder(Order order){
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println("createOrder 메서드 실행 스레드: " + threadName);
            // 주문 확인
            Thread.sleep(2000); // 주문 확인 시간 시뮬레이션
            order.setStatus("조리 중");
            messagingTemplate.convertAndSend("/topic/order", "주문이 조리 중입니다: " + order.getOrderId());
            System.out.println("조리중"+order.getOrderId());

            // 조리 시간
            Thread.sleep(5000); // 조리 시간 시뮬레이션
            order.setStatus("배달 중");
            messagingTemplate.convertAndSend("/topic/order", "주문이 배달 중입니다: " + order.getOrderId());
            System.out.println("배달중"+order.getOrderId());

            // 배달 시간
            Thread.sleep(3000); // 배달 시간 시뮬레이션
            order.setStatus("배달 완료");
            messagingTemplate.convertAndSend("/topic/order", "주문이 배달 완료되었습니다: " + order.getOrderId());
            System.out.println("배달 완료"+order.getOrderId());

            System.out.println("주문 처리 최종 완료: " + order.getOrderId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
