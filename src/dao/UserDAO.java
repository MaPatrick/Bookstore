package dao;

import pojo.User;

/**
 * @author Ma
 * @create 2021-07-30-0:55
 */
public interface UserDAO {
    /**
     * 根据用户名查询用户信息
     * @return如果返回null,说明没有这个用户，反之亦然
     */
    public User queryUserByUsername(String name);
    /**
     * 根据用户名和密码查询用户信息
     * @return如果返回null,说明用户名或密码错误，反之亦然
     */
    public User queryUserByUsernameAndUserPassword(String name,String password);

    /**
     * 保存用户信息
     * @return
     */
    public int saveUser(User user);
}
