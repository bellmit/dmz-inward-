package dmztest.utils;

import com.dmz.service.utils.GenerateUUID;
import org.junit.Test;

/**
 * Created by dmz on 2016/3/1.
 */
public class GenerateUUIDTest {
    @Test
    public void testGenerateUUID(){
        System.out.println(GenerateUUID.getUUID());
    }
}
