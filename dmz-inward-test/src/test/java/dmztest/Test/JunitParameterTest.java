package dmz.Junit.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * @author dmz
 * @date 2017/1/5
 */
@RunWith(Parameterized.class)
public class JunitParameterTest {

    private String greeting;

    public JunitParameterTest(String greeting) {
        super();
        this.greeting = greeting;
    }

    @Test
    public void testParameter() {
        System.out.println(greeting);
    }

    @Test
    public void test() {

    }

    @Parameterized.Parameters
    public static List<String[]> getParams() {
        return Arrays.asList(new String[][]{{"Hello"},{"World"}});
    }
}
