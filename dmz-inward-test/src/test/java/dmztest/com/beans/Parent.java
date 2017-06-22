package dmztest.com.beans;

/**
 * Created by dmz on 2016/1/23.
 */
public class Parent {
    public Parent(){
        System.out.println("This is a Parent!");
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    private Son son;
}
