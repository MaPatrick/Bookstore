package test;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import org.junit.Test;
import pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author mrs
 * @create 2021-08-12  15:19
 */
public class OrderDAOImplTest {
    OrderDAO dao = new OrderDAOImpl();
    @Test
    public void saveOrder() {
       dao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0,1));
    }
}