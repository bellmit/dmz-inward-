package dmztest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by dmz on 2016/11/18.
 */
public class FeatureOneTest {

    @Test
    public void testFeatureOne() {
        Assert.assertTrue(true);
    }

    @Test
    @Ignore
    public void testFeatureOneIgnore() {
        Assert.assertTrue(true);
    }
}
