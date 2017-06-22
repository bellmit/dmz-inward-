package dmztest.pipeline;

import java.util.Map;

/**
 * @author dmz
 * @date 2017/1/22
 */
public interface PiplineContext {
    void invokeNext(Map<String, Object> params);
}
