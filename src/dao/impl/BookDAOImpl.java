package dao.impl;

import dao.BookDAO;
import pojo.Book;
import utils.BaseDAO;

import java.util.List;

/**
 * @author mrs
 * @create 2021-08-06  18:32
 */
public class BookDAOImpl extends BaseDAO implements BookDAO {

    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO `t_book`(`name`,`price`,`author`,`sales`,`stock`,`img_path`)VALUES(?,?,?,?,?,?);";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "DELETE FROM `t_book` WHERE id = ?;";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE `t_book` SET `name`=?,`price`=?,`author`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?;";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "SELECT `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` imgPath FROM `t_book` WHERE `id`=?;";
        return dangetInstance(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "SELECT `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` imgPath FROM `t_book`;";
        return duogetInstance(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from `t_book`";
        Number value = getValue(sql);
        return value.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "SELECT `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` imgPath FROM `t_book` limit ?,?";
        return duogetInstance(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from `t_book` where price between ? and ?";
        Number value = getValue(sql,min,max);
        return value.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "SELECT `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` imgPath FROM `t_book` " +
                "WHERE `price` BETWEEN ? AND ? order by price LIMIT ?,?;";
        return duogetInstance(Book.class,sql,min,max,begin,pageSize);
    }
}
