package dmztest.utils;

import com.dmz.service.utils.ExcelRD;
import org.junit.Test;

/**
 * Created by dmz on 2016/3/22.
 */
public class ExcelRDTest {
    @Test
    public void testReadExcel(){
        ExcelRD.ReadExcel("C:/Users/dmz/IdeaProjects/javaio/newdmz.xlsx");
    }
}
