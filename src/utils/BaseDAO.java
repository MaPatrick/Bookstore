package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author Ma
 * @create 2021-07-30-0:00
 */
public class BaseDAO {
    QueryRunner runner = new QueryRunner();
    /**
     * 获取数据库连接池中的连接
     */
    private static DataSource source;
    static {
        try {
            Properties pros = new Properties();
            InputStream is = BaseDAO.class.getClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    /**
     * 关闭数据库！！
     * @param conn
     */
    public static void closeResource(Connection conn){
        DbUtils.closeQuietly(conn);
    }


    /**
     * 增删改通操作
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql,Object...args){
        int count=0;
        Connection conn = BaseDAO.getConnection();
        try {
            runner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            BaseDAO.closeResource(conn);
        }
        return count;
    }

    /**
     * 单行查询
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T dangetInstance(Class<T> clazz,String sql,Object...args){
        Connection conn = BaseDAO.getConnection();
        T query = null;
        try{
            BeanHandler<T> handler = new BeanHandler<T>(clazz);
            query = runner.query(conn,sql,handler,args);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            BaseDAO.closeResource(conn);
        }
        return query;

    }


    /**
     * 多行查询
     * @param sql
     * @param args
     * @return
     */
    public <T> List<T> duogetInstance(Class<T> clazz,String sql,Object...args){
        Connection conn = BaseDAO.getConnection();
        List<T> list = null;
        try{
            BeanListHandler<T> ListHandler = new BeanListHandler<T>(clazz);
            list = runner.query(conn,sql,ListHandler,args);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            BaseDAO.closeResource(conn);
        }
        return list;
    }


    /**
     * 特殊值查询
     * @param sql
     * @param <E>
     * @return
     */
    public <E> E getValue(String sql,Object...args){
        E query = null;
        Connection conn = BaseDAO.getConnection();
        try{
            ScalarHandler handler = new ScalarHandler();
            query = (E)runner.query(conn,sql,handler,args);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            BaseDAO.closeResource(conn);
        }
        return query;
    }

}
