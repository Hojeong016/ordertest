package org.hj.ordertest;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

//역할 : 비동기 작업 수행
@Service
@RequiredArgsConstructor
public class OrderService {

    private final SimpMessagingTemplate messagingTemplate;
    private final OwnerService ownerService;
    //메서드가 호출되면 별도의 스레드 풀에서 실행되므로 메인 스레드가 블로킹되지 않습니다.
    @Async
    public CompletableFuture<String> createOrder(String orderId){
        try{
            String threadName = Thread.currentThread().getName();
            System.out.println("createOrder 메서드 실행 스레드: " + threadName);

            Order order = new Order();
            order.setOrderId(orderId);

            Thread.sleep(5000);
            System.out.println("주문 처리 완료 : " + orderId);

            messagingTemplate.convertAndSend("/topic/order", orderId);

            ownerService.processOrder(order);
            return CompletableFuture.completedFuture("주문 처리 성공:" + orderId);
        } catch (Exception e) {
            e.printStackTrace();

            CompletableFuture<String> faildfuture = new CompletableFuture<>();
            faildfuture.completeExceptionally(e);
            return faildfuture;
        }
    }
}
