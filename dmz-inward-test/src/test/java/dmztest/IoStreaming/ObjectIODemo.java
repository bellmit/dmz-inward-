package dmztest.IoStreaming;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIODemo {
    public static void main(String[] args)
            throws Exception{
        String file = "obj.dat";
        ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(file)));
        Foo foo =new Foo();
        out.writeObject(foo);//将foo引用的对象，序列化到文件中
        out.writeObject(foo);
        out.close();

        //读出
        ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(file)));
        Foo foo1 = (Foo)in.readObject();//对象反序列化
        Foo foo2 =(Foo)in.readObject();
        in.close();
        System.out.println(foo1 == foo2);
        System.out.println(foo1.name == foo2.name);
        System.out.println("深层复制：对象被复制，对象属性也被复制");
        System.out.println(foo==foo1);//false 对象复制了(一层)
        System.out.println(foo.name == foo1.name);//false ,属性被复制了(二层)
        //利用序列化 和 反序列化  可以简洁的实现 对象的深层复制
    }

}
class Foo implements Serializable{//Serializable没有声明方法
    String name = "Tom";
}
