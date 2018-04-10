package dmztest.exceptionTest;

import java.net.ConnectException;

/**
 * @author dmz
 * @date 2018/1/25
 */
public class AThrowAImpl implements AThrowA {

    public  AThrowAImpl() {

    }
    @Override
    public String a() throws ConnectException {
        System.out.println("Hello");
        throw new ConnectException();
    }
}
