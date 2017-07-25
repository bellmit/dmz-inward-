package com.dmz.service.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * @author dmz
 * @date 2017/7/13
 */
@Activate(group = Constants.CONSUMER)
public class TracingDubboConsumer implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("Dubbo Consumer Filter");
        Result result = invoker.invoke(invocation);
        return result;
    }
}
