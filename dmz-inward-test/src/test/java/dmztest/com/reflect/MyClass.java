package dmztest.com.reflect;

/**
 * Created by dmz on 2016/1/28.
 */
public class MyClass {
    public int count;
    public MyClass(int count){
        this.count = count;
    }
    public void inCrease(int start){
        this.count+=start;
    }
    public int getCount(){
        return this.count;
    }
}
