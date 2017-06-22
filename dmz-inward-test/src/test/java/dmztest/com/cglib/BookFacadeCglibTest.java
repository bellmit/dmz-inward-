package dmztest.com.cglib;

/**
 * Created by dmz on 2016/1/28.
 */
public class BookFacadeCglibTest {
    public static void main(String[] args){
        BookFacadeCglib cglib = new BookFacadeCglib();
        BookFacade proxy = (BookFacadeImpl)cglib.getInstance(new BookFacadeImpl());
        proxy.addBook();
    }
}
