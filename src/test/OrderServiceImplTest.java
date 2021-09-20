package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author mrs
 * @create 2021-08-12  19:22
 */
public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java从入门到崩溃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java从入门到崩溃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是:"+orderService.createOrder(cart,1));
    }
}