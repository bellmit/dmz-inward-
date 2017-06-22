package dmztest.pipeline;

import java.util.Map;

/**
 * @author dmz
 * @date 2017/1/22
 */
public class HelloValve implements Valve {

    @Override
    public void invoke(Map<String, Object> params, PiplineContext piplineContext) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            System.out.printf("Key:" + entry.getKey() + "\tValue:" + entry.getValue() + "\n");
        }
    }
}
