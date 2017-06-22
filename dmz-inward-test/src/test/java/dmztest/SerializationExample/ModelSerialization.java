package dmztest.SerializationExample;

import java.io.Serializable;

/**
 * Created by dmz on 2016/10/21.
 */
public class ModelSerialization implements Serializable {

    private static final long serialVersionUID = -5794427590103770488L;
    private String addr;

    private SubModel subModel;

    public ModelSerialization(String addr, SubModel subModel) {
        this.addr = addr;
        this.subModel = subModel;
    }

    public static void main(String[] args) {
//        Integer a = new Integer(10);
//        System.out.println(a.toString());
        Object o = null;
        System.out.println(String.valueOf(o));

    }
}

