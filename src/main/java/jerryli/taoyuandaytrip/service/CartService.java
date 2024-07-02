package jerryli.taoyuandaytrip.service;

import jerryli.taoyuandaytrip.pojo.Cart;
import jerryli.taoyuandaytrip.pojo.CartItem;
import jerryli.taoyuandaytrip.pojo.User;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-29-上午 10:38
 */
public interface CartService {

    public List<CartItem> getCartItemByUserId(Integer userId);

    public int addCartItem(CartItem cart);

    public int deleteCartItem(Integer cartItemId);

    public Cart getCart(Integer userId);

}
