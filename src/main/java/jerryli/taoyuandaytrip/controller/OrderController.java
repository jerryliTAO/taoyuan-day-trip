package jerryli.taoyuandaytrip.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jerryli.taoyuandaytrip.pojo.Order;
import jerryli.taoyuandaytrip.pojo.StatusResponse;
import jerryli.taoyuandaytrip.pojo.request.OrderRequest;
import jerryli.taoyuandaytrip.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-07-03-下午 03:07
 */
@Tag(name="Order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Checkout the order")
    @PostMapping("/api/order")
    public ResponseEntity<StatusResponse> addOrder(@RequestBody OrderRequest orderRequest){
        String result = orderService.addOrder(orderRequest);
        StatusResponse statusResponse = new StatusResponse();
        if(!(result.equals("付款失敗，請洽發卡銀行") || result.equals("下訂完成"))){
           statusResponse.setStatus("伺服器內部發生錯誤");
        }else {
            statusResponse.setStatus(result);
        }
        return ResponseEntity.ok(statusResponse);
    }

    @Operation(summary = "Get history order by user ID")
    @GetMapping("/api/order/{userId}")
    public List<Order> getHistoryOrder(@PathVariable("userId") Integer userId){
        List<Order> historyOrder = orderService.getHistoryOrder(userId);
        return historyOrder;

    }
}
