package jerryli.taoyuandaytrip.mapper;

import jerryli.taoyuandaytrip.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-07-03-下午 10:50
 */
public interface OrderMapper {

    public int addOrder(@Param("newOrder") Order order);

    public List<Order> getHistoryOrderByUserId(@Param("userId")Integer userId);

}
