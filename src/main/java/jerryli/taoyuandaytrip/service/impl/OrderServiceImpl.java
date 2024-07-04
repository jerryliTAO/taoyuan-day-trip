package jerryli.taoyuandaytrip.service.impl;

import com.alibaba.fastjson2.JSONObject;
import jerryli.taoyuandaytrip.mapper.AttractionMapper;
import jerryli.taoyuandaytrip.mapper.OrderMapper;
import jerryli.taoyuandaytrip.pojo.Order;
import jerryli.taoyuandaytrip.pojo.request.OrderRequest;
import jerryli.taoyuandaytrip.service.CartService;
import jerryli.taoyuandaytrip.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jerry
 * @create 2024-07-03-下午 04:31
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AttractionMapper attractionMapper;

    @Override
    public String addOrder(OrderRequest orderRequest) {
        // post data
        Map cardHolder = new HashMap<>();
        cardHolder.put("name",orderRequest.getName());
        cardHolder.put("email",orderRequest.getEmail());
        cardHolder.put("phone_number", orderRequest.getPhone_number());

        JSONObject data = new JSONObject();
        data.put("prime",orderRequest.getPrime());
        data.put("partner_key","partner_6ID1DoDlaPrfHw6HBZsULfTYtDmWs0q0ZZGKMBpp4YICWBxgK97eK3RM");
        data.put("merchant_id", "GlobalTesting_CTBC");
        data.put("details","TapPay Test");
        data.put("amount",orderRequest.getPrice());
        data.put("cardholder",cardHolder);
        data.put("remember",true);

        // post headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("x-api-key","partner_6ID1DoDlaPrfHw6HBZsULfTYtDmWs0q0ZZGKMBpp4YICWBxgK97eK3RM");

        final String URL = "https://sandbox.tappaysdk.com/tpc/payment/pay-by-prime";


        HttpEntity httpEntity = new HttpEntity<>(data, httpHeaders);
        // call tappay api and get response
        String jsonObject = restTemplate.postForObject(URL, httpEntity, String.class);
        JSONObject response = JSONObject.parseObject(jsonObject);
        System.out.println(response);

        //
        long orderTimeMillis = System.currentTimeMillis();
        LocalDateTime orderTime = Instant.ofEpochMilli(orderTimeMillis).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();

        Integer status = (Integer) response.get("status");
         if(status == 0){
             // create order number
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
             String current_date = dateFormat.format(orderTimeMillis);
             String orderNumber = orderRequest.getUserId() + "_" + current_date;

             // save order detail
             Order order = Order.builder()
                     .userId(orderRequest.getUserId())
                     .name(orderRequest.getName())
                     .email(orderRequest.getEmail())
                     .phone(orderRequest.getPhone_number())
                     .totalPrice(orderRequest.getPrice())
                     .number(orderNumber)
                     .orderTime(orderTime)
                     .build();

             int addOrderResult = orderMapper.addOrder(order);
             if(addOrderResult != 1){
                 return "新增訂單失敗";
             }

             // according orderNumber get orderId(Auto Generated)
             int updateOrderNumberResult = cartService.updateOrderNumber(order.getUserId(), order.getId());
             if(updateOrderNumberResult <= 0){
                 return "更新訂單號碼失敗";
             }

             //return result
             return "下訂完成";

         }else{
             //問題碼
             return "付款失敗，請洽發卡銀行";
         }
    }

    public List<Order> getHistoryOrder(Integer userId){
        return orderMapper.getHistoryOrderByUserId(userId);
    }
}
