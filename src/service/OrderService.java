package service;

import com.sun.org.apache.regexp.internal.CharacterArrayCharacterIterator;
import pojo.Cart;

/**
 * @author mrs
 * @create 2021-08-12  19:06
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

}
