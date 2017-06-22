package dmztest.pipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dmz
 * @date 2017/1/22
 */
public class PiplineTest {
    public static void main(String[] args) {
        Valve basic = new HelloValve();

        Valve beforeValve = new BeforeValve();
        Valve aroundValve = new AroundValve();
        Valve afterValve = new AfterValve();

        Pipline pipline = new SimplePipline();
        pipline.setBasic(basic);
        pipline.setValve(beforeValve);
        pipline.setValve(afterValve);
        pipline.setValve(aroundValve);

        Map<String, Object> params = new HashMap<>();
        params.put("A", 10);
        params.put("B", 20);
        pipline.invoke(params);
    }
}
