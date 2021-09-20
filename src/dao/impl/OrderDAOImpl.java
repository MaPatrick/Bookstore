package dao.impl;

import dao.OrderDAO;
import pojo.Order;
import utils.BaseDAO;

/**
 * @author mrs
 * @create 2021-08-12  12:45
 */
public class OrderDAOImpl extends BaseDAO implements OrderDAO {

    @Override
    public int saveOrder(Order order) {
        String sql="INSERT INTO `t_order`(`order_id`,`create_time`,`price`,`status`,`user_id`)VALUES(?,?,?,?,?);";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
