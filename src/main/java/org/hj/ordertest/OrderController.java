package org.hj.ordertest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

// 사용자로부터 주문 요청을 받음
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/{orderId}")
    public CompletableFuture<String> placeOrder(@PathVariable("orderId") String orderId) {
        return orderService.createOrder(orderId)
                .thenApply(order -> {
                    // 주문 생성 후 즉시 응답
                    return "주문이 접수되었습니다: " + orderId;
                })
                .exceptionally(ex -> {
                    // 예외 발생 시 처리
                    ex.printStackTrace();
                    return "주문 처리에 실패했습니다: " + ex.getMessage();
                });
    }



}
