package test;

import java.lang.reflect.Method;

/**
 * @author mrs
 * @create 2021-08-06  11:54
 */
public class UserServletTest {
    public void login(){
        System.out.println("这是login()方法调用了");
    }
    public void regist(){
        System.out.println("这是regist()方法调用了");
    }
    public void updateUser(){
        System.out.println("这是updateUser()方法调用了");
    }
    public void updateUserPassword(){
        System.out.println("这是updateUserPassword()方法调用了");
    }

    public static void main(String[] args){
        String action = "login";
        try {
            //获取action业务鉴别字符串，获取相应得业务方法，反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(method);
            //调用目标业务，方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
