package dao;

import pojo.Order;
import pojo.OrderItem;

/**
 * @author mrs
 * @create 2021-08-12  12:43
 */
public interface OrderDAO {
    public int saveOrder(Order order);

}
