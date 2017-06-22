package dmztest.pipeline;

import java.util.Map;

/**
 * @author dmz
 * @date 2017/1/22
 */
public class SimplePipline implements Pipline {

    private Valve[] valves = new Valve[0];

    private Valve basic;

    @Override
    public void setBasic(Valve valve) {
        this.basic = valve;
    }

    @Override
    public void setValve(Valve valve) {
        Valve[] newValves = new Valve[valves.length + 1];
        System.arraycopy(valves, 0, newValves, 0, valves.length);
        newValves[newValves.length - 1] = valve;
        valves = newValves;
    }

    @Override
    public void invoke(Map<String, Object> params) {
        new SimplePiplineContext().invokeNext(params);
    }

    private class SimplePiplineContext implements PiplineContext {

        private int step;

        @Override
        public void invokeNext(Map<String, Object> params) {
            int currentValve = step;
            step++;
            if (currentValve < valves.length) {
                valves[currentValve].invoke(params, this);
            } else {
                basic.invoke(params, this);
            }
        }
    }
}
