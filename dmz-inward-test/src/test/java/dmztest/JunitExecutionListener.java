package dmztest;

import com.alibaba.fastjson.JSON;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * Created by dmz on 2016/11/18.
 */
public class JunitExecutionListener extends RunListener {

    /*Called before any testCase have been run*/
    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
        System.out.println("RunStarted:" + JSON.toJSONString(description));
    }

    /*Called when all testCase have finished*/
    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        System.out.println("RunFinished:" + JSON.toJSONString(result));
    }

    /*Called when an atomic test is about to be started*/
    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
        System.out.println("Started:" + JSON.toJSONString(description));
    }

    /*Called when an atomic test has finished, whether the test succeeds or fails*/
    @Override
    public void testFinished(Description description) throws Exception {
        super.testFinished(description);
        System.out.println("Finished:" + JSON.toJSONString(description));
    }

    /*Called when an atomic test fails*/
    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
        System.out.println("Failure:" + JSON.toJSONString(failure));
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        super.testAssumptionFailure(failure);
        System.out.println("AssumptionFailure:" + JSON.toJSONString(failure));
    }

    /*Called when a test will not be run, generally because a test method is annotated with Ignore*/
    @Override
    public void testIgnored(Description description) throws Exception {
        super.testIgnored(description);
        System.out.println("Ignored:" + JSON.toJSONString(description));
    }
}
