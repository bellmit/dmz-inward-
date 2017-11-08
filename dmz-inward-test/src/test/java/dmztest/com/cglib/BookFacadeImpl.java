package dmztest.com.cglib;

/**
 * Created by dmz on 2016/1/28.
 */
public class BookFacadeImpl implements BookFacade{
    public void addBook() {
        System.out.println("add a book...");
        this.addBook2();
    }

    @Override
    public void addBook2() {
        System.out.println("add a book 2...");
    }
}
