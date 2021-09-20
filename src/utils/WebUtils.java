package utils;

import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author mrs
 * @create 2021-08-06  15:12
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            /**
             * 把所有请求的参数都注入到user对象中
             */
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int paresInt(String strInt,int defaultValue){
        try {
            if(strInt != null) {
                return Integer.parseInt(strInt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
