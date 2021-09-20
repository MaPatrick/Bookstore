package service;

import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @author mrs
 * @create 2021-08-07  11:15
 */
public interface BookService {
    public void addbook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
