package test;

import dao.impl.BookDAOImpl;
import org.junit.Test;
import pojo.Book;
import pojo.Page;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;


/**
 * @author mrs
 * @create 2021-08-06  23:34
 */
public class BookDAOImplTest {
    private BookDAOImpl dao = new BookDAOImpl();
    @Test
    public void addBook() {
        dao.addBook(new Book(null,"我为什么这么帅","国哥",new BigDecimal(999),11000,0,null));
    }

    @Test
    public void deleteBookById() {
        dao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        dao.updateBook(new Book(21,"大家怎样能像我这么帅","国哥",new BigDecimal(999),11000,0,null));
    }

    @Test
    public void queryBookById() {
        Book book = dao.queryBookById(20);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = dao.queryBooks();
        Iterator<Book> iterator = books.iterator();
        while(iterator.hasNext()){
            Book next = iterator.next();
            System.out.println(next);
        }
    }
    @Test
    public void queryForPageTotalCount(){
        System.out.println(dao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountByPrice(){
        System.out.println(dao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItems() {
        for(Book book:dao.queryForPageItems(8, Page.PAGE_SIZE)){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageItemsByPrice() {
        for(Book book:dao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,50)){
            System.out.println(book);
        }
    }
}
