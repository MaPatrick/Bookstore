package dao.impl;

import dao.UserDAO;
import pojo.User;
import utils.BaseDAO;

import java.util.Map;

/**
 * @author Ma
 * @create 2021-07-30-1:02
 */
public class UserDAOImpl extends BaseDAO implements UserDAO{
    @Override
    public User queryUserByUsername(String name) {
        String sql = "SELECT `id`,`username`,`password`,`email` FROM `t_user` WHERE `username` = ?;";
        return dangetInstance(User.class,sql,name);
    }

    @Override
    public User queryUserByUsernameAndUserPassword(String name, String password) {
        String sql = "SELECT `id`,`username`,`password`,`email` FROM `t_user` WHERE `username` = ? and password = ?;";
        return dangetInstance(User.class,sql,name,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO `t_user`(`username`,`password`,`email`) VALUES(?,?,?);";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
