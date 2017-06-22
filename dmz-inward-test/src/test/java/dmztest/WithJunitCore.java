package dmztest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmz on 2016/11/18.
 */
public class WithJunitCore {
    public static void main(String[] args) {

        List<Class> testCases = new ArrayList();
        testCases.add(FeatureOneTest.class);
        testCases.add(FeatureTwoTest.class);
        JUnitCore runner = new JUnitCore();
        runner.addListener(new JunitExecutionListener());
        for (Class clazz : testCases) {
            System.out.println("=========="+clazz.getName()+"==========");
//            Result result = JUnitCore.runClasses(clazz);
            Result result = runner.run(clazz);
            System.out.println("FailureCount:" + result.getFailureCount());
            System.out.println("IgnoreCount:" + result.getIgnoreCount());
            System.out.println("RunCount:" + result.getRunCount());
            System.out.println("RunTime:"+result.getRunTime());
            for (Failure failure : result.getFailures()) {
                System.out.println("Failure:"+failure.getMessage());
            }
            System.out.println("=========="+clazz.getName()+"==========");
        }

    }
}
