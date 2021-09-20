package test;

import dao.impl.UserDAOImpl;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.*;

/**
 * @author Ma
 * @create 2021-07-30-1:21
 */
public class UserDAOImplTest {
    UserDAOImpl dao = new UserDAOImpl();
    @Test
    public void queryUserByUsername() {
        System.out.println(dao.queryUserByUsername("mrs0815"));
    }

    @Test
    public void queryUserByUsernameAndUserPassword() {
        System.out.println(dao.queryUserByUsernameAndUserPassword("mrs0815","123456"));
    }

    @Test
    public void saveUser() {
        System.out.println(dao.saveUser(new User(null,"nk53719","53719","nk@126.com")));
    }

}