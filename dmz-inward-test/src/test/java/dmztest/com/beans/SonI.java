package dmztest.com.beans;

import org.springframework.stereotype.Service;

/**
 * Created by dmz on 2016/2/19.
 */
@Service(value = "dmz")
public class SonI implements ISon {

    public void cao() {
        System.out.println("CAO");
    }
}
