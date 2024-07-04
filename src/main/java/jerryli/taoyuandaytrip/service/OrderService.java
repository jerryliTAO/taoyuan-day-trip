package jerryli.taoyuandaytrip.service;

import jerryli.taoyuandaytrip.pojo.Order;
import jerryli.taoyuandaytrip.pojo.request.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-07-03-下午 04:30
 */
public interface OrderService {
    public String addOrder(OrderRequest orderRequest);

    public List<Order> getHistoryOrder(Integer userId);
}
