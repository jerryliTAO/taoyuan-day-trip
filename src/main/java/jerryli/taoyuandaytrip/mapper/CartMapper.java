package jerryli.taoyuandaytrip.mapper;

import jerryli.taoyuandaytrip.pojo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-28-下午 10:48
 */
public interface CartMapper {
    public List<CartItem> getCartItemByUserId(@Param("userId") Integer userId);

    public int addCartItem(@Param("cartItem") CartItem cartItem);

    public int updateOrderId(@Param("cartItem") CartItem cartItem);

    public int deleteCartItem(@Param("cartItem") CartItem cartItem);
}
