package dmztest.pipeline;

import java.util.Map;

/**
 * @author dmz
 * @date 2017/1/22
 */
public interface Pipline {

    void setBasic(Valve valve);

    void setValve(Valve valve);

    void invoke(Map<String, Object> params);
}
