package dmztest.pipeline;

import java.util.Map;

/**
 * @author dmz
 * @date 2017/1/22
 */
public class AroundValve implements Valve {

    @Override
    public void invoke(Map<String, Object> params, PiplineContext piplineContext) {
        System.out.println("Start:");
        piplineContext.invokeNext(params);
        System.out.println("End:");
    }
}
