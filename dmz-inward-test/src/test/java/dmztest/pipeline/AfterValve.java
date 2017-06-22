package dmztest.pipeline;

import java.util.Map;

/**
 * @author dmz
 * @date 2017/1/22
 */
public class AfterValve implements Valve {

    @Override
    public void invoke(Map<String, Object> params, PiplineContext piplineContext) {
        piplineContext.invokeNext(params);
        System.out.println("After:");
    }
}
