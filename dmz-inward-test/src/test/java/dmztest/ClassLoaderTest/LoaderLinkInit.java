package dmztest.ClassLoaderTest;

import java.io.Serializable;

/**
 * @author dmz
 * @date 2017/3/21
 */
class A{
    public static int anInt = 10;

    private static int SetInt() {
        System.out.println("Set Int");
        return anInt = 1;
    }
}

class BillPughSingleto implements Serializable {
    private static final long serialVersionUID = -5101789965902466021L ;

    private int anInt = 200;

    public int getAnInt () {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this .anInt = anInt ;
    }

    private BillPughSingleto() {
        System.out.println("BillPughSingleto");
    }
    private static class SingletoHolder {
        static final BillPughSingleto instance = new BillPughSingleto();
    }

    public static BillPughSingleto getInstance() {
        return SingletoHolder.instance;
    }

    protected Object readResolve() {
        //        This method will be invoked when you will de-serialize the object.
//        Inside this method,
//        you must return the existing instance to ensure single instance application wide
        return getInstance() ;
    }
}
public class LoaderLinkInit {
    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader loader = LoaderLinkInit.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }
}
