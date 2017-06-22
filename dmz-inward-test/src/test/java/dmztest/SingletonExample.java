package dmztest;

import java.io.*;

/**
 * Created by dmz on 2016/8/31.
 */
class EagerSingleto {
    private static volatile EagerSingleto instance = new EagerSingleto();

    private EagerSingleto() {
    }

    public static EagerSingleto getInstance() {
        return instance;
    }
}

class LazySingleto implements Serializable{

    private static final long serialVersionUID = 3179266004702202913L;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    private int anInt = 100;
    private static volatile LazySingleto instance = null;

    private LazySingleto() {

    }

    protected Object readResolve() {
//        This method will be invoked when you will de-serialize the object.
//        Inside this method,
//        you must return the existing instance to ensure single instance application wide
        return instance;
    }

    public static LazySingleto getInstance() {
        if (instance == null) {
            synchronized (LazySingleto.class) {
//                double check
                if (instance == null) {
                    instance = new LazySingleto();
                }
            }
        }
        return instance;
    }

}

class StaticBlockInstance {

    private static volatile StaticBlockInstance instance = null;
    static {
        instance = new StaticBlockInstance();
    }
    private StaticBlockInstance() {
    }
    public static StaticBlockInstance getInstance() {
        return instance;
    }

}

/**
 * 建议使用此方案
 */
class BillPughSingleto implements Serializable {
    private static final long serialVersionUID = -5101789965902466021L;

    private int anInt = 200;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    private BillPughSingleto() {

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
        return getInstance();
    }
}
public class SingletonExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        LazySingleto instance = LazySingleto.getInstance();
        BillPughSingleto instance = BillPughSingleto.getInstance();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("a.a"));
        objectOutputStream.writeObject(instance);
        objectOutputStream.close();

        instance.setAnInt(20);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("a.a"));
        BillPughSingleto instance2 = (BillPughSingleto) objectInputStream.readObject();
        System.out.println("Instance1:" + instance.getAnInt());
        System.out.println("Instance2:" + instance2.getAnInt());
    }
}
