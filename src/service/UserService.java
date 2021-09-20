package service;

import pojo.User;

/**
 * @author Ma
 * @create 2021-07-30-10:35
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return如果返回null,说明登录失败，返回有值就是登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param name
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String name);
}
