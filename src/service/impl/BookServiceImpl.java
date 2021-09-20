package service.impl;

import dao.BookDAO;
import dao.impl.BookDAOImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;

import java.util.List;

/**
 * @author mrs
 * @create 2021-08-07  11:18
 */
public class BookServiceImpl implements BookService {
private BookDAO dao = new BookDAOImpl();
    @Override
    public void addbook(Book book) {
        dao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        dao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        dao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return dao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return dao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {

        Page<Book> page = new Page<Book>();


        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = dao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount /pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据的开始索引
        int begin = (page.getPageNo()-1)*pageSize;
        //求当前页数据
        List<Book> items = dao.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();


        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = dao.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount /pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据的开始索引
        int begin = (page.getPageNo()-1)*pageSize;
        //求当前页数据
        List<Book> items = dao.queryForPageItemsByPrice(begin,pageSize,min,max);
        //设置当前页数据
        page.setItems(items);

        return page;
    }
}
