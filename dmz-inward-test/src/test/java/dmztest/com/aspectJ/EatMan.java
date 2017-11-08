package dmztest.com.aspectJ;

/**
 * @author dmz
 * @date 2017/9/14
 */
public class EatMan {
    public void eat(){
        System.out.println("eat food");
        this.eat2();
    }

    public void eat2() {
        System.out.println("eat food 2...");
    }
}
