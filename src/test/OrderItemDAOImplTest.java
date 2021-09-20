package test;

import dao.OrderItemDAO;
import dao.impl.OrderItemDAOImpl;
import org.junit.Test;
import pojo.OrderItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author mrs
 * @create 2021-08-12  19:00
 */
public class OrderItemDAOImplTest {

    @Test
    public void saveOrderItem() {
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
        orderItemDAO.saveOrderItem(new OrderItem(null,"java从入门到崩溃",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDAO.saveOrderItem(new OrderItem(null,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDAO.saveOrderItem(new OrderItem(null,"PHP入门到出轨",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));


    }
}