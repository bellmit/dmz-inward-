package dmztest.IoStreaming;

import java.io.*;

/**
 * @author dmz
 * @date 2017/2/27
 */
class A implements Serializable, Cloneable {

    String bb = "TOM";
    B b = new B();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class B implements Serializable {

}

public class CopyObject {

    private static void shallowCopy() throws CloneNotSupportedException {
        A a = new A();
        A ashallow = (A) a.clone();
        System.out.println(a == ashallow);
        System.out.println(a.b == ashallow.b);
        System.out.println(a.bb == ashallow.bb);
    }

    private static void deepCopy() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(arrayOutputStream);
        A a = new A();
        objectOut.writeObject(a);

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
        ObjectInputStream objectIn = new ObjectInputStream(arrayInputStream);
        A adeepCopy = (A) objectIn.readObject();
        System.out.println(a == adeepCopy);
        System.out.println(a.b == adeepCopy.b);
        System.out.println(a.bb == adeepCopy.bb);
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        shallowCopy();
        System.out.println("========================================");
        deepCopy();
        String a = "TOM";
        String b = "TOM";
        System.out.println(a == b);
    }
}
