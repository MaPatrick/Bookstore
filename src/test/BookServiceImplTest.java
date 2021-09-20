package test;

import org.junit.Test;
import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author mrs
 * @create 2021-08-07  11:22
 */
public class BookServiceImplTest {
private BookService service = new BookServiceImpl();
    @Test
    public void addbook() {
        service.addbook(new Book(null,"我为什么这么帅","国哥",new BigDecimal(999),11000,0,null));
    }

    @Test
    public void deleteBookById() {
        service.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        service.updateBook(new Book(22,"大家怎样能像我这么帅","国哥",new BigDecimal(999),11000,0,null));
    }

    @Test
    public void queryBookById() {
        Book book = service.queryBookById(22);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = service.queryBooks();
        for(Book s:books){
            System.out.println(s);
        }
    }
    @Test
    public void page(){
        System.out.println(service.page(1, Page.PAGE_SIZE));
    }
    @Test
    public void pageByPice(){
        System.out.println(service.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}