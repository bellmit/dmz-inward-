package dmztest;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * Created by dmz on 2016/8/4.
 */
 class FinalizeGcAvoid implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("try...");
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println("catch...");
        } finally {
            System.out.println("finally...");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize...");
    }
}
public class finalizeTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        new Thread((Runnable) finalizeTest.class.getClassLoader().loadClass("net.frontnode.openapi.FinalizeGcAvoid").newInstance()).start();
//        for (int i = 0; i<10 ; i++) {
//            new Thread(new FinalizeGcAvoid()).start();
////            Runtime.runFinalizersOnExit(true);
//        }
//        System.gc();
//        Runtime.runFinalizersOnExit(true);
//        Runtime.getRuntime().runFinalization();
//        System.runFinalization();
        Runtime rTime = Runtime.getRuntime();
        System.out.println(JSON.toJSONString(rTime));
        new HashMap<>().get(null);

        new String();
        new StringBuffer();

    }
}
