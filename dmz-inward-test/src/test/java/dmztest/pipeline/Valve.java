package dmztest.pipeline;

import java.util.Map;

/**
 * @author dmz
 * @date 2017/1/22
 */
public interface Valve {
    void invoke(Map<String, Object> params, PiplineContext piplineContext);
}
