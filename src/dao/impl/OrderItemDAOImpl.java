package dao.impl;

import dao.OrderItemDAO;
import pojo.OrderItem;
import utils.BaseDAO;

/**
 * @author mrs
 * @create 2021-08-12  12:51
 */
public class OrderItemDAOImpl extends BaseDAO implements OrderItemDAO {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="INSERT INTO `t_order_item`(`name`,`count`,`price`,`total_price`,`order_id`)VALUES(?,?,?,?,?);";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
