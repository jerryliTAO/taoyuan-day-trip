package jerryli.taoyuandaytrip.service.impl;

import jerryli.taoyuandaytrip.mapper.AttractionMapper;
import jerryli.taoyuandaytrip.mapper.CartMapper;
import jerryli.taoyuandaytrip.mapper.UserMapper;
import jerryli.taoyuandaytrip.pojo.Attraction;
import jerryli.taoyuandaytrip.pojo.Cart;
import jerryli.taoyuandaytrip.pojo.CartItem;
import jerryli.taoyuandaytrip.pojo.User;
import jerryli.taoyuandaytrip.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jerry
 * @create 2024-05-29-下午 01:47
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    AttractionMapper attractionMapper;
    @Autowired
    UserMapper userMapper;


    private Integer totalPrice;
    @Override
    public List<CartItem> getCartItemByUserId(Integer userId) {
        List<CartItem> cartItemList = cartMapper.getCartItemByUserId(userId);
        return cartItemList;
    }

    @Override
    public int addCartItem(CartItem cart) {
        return cartMapper.addCartItem(cart);
    }

    @Override
    public int deleteCartItem(Integer cartItemId) {
        return cartMapper.deleteCartItem(cartItemId);
    }

    @Override
    public Cart getCart(Integer userId) {
        List<CartItem> cartItemList = getCartItemByUserId(userId);
         this.totalPrice = 0;
        for (CartItem cartItem : cartItemList) {
            this.totalPrice = this.totalPrice + cartItem.getPrice();
        }
        User user = userMapper.getUserById(userId);
        return new Cart(user, this.totalPrice, cartItemList);
    }
}
