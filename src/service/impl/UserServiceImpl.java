package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import pojo.User;
import service.UserService;

/**
 * @author Ma
 * @create 2021-07-30-10:39
 */
public class UserServiceImpl implements UserService {
    private UserDAO dao = new UserDAOImpl();

    @Override
    public void registUser(User user) {
        dao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return dao.queryUserByUsernameAndUserPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String name) {
        if(dao.queryUserByUsername(name) == null){
            //等于null，说明，没查到表示可用
            return false;
        }
        return true;
    }
}
