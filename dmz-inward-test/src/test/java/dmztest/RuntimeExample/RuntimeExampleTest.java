package dmztest.RuntimeExample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dmz on 2016/8/25.
 */
public class RuntimeExampleTest {
    public static void main(String[] args) throws InterruptedException {
//        Runtime.getRuntime().addShutdownHook(new Thread(()-> System.out.println("Execute ShutdownHook.")));
//        System.exit(0);
//        System.out.println("processors size:"+Runtime.getRuntime().availableProcessors());
//        System.out.println("max memory size:" + Runtime.getRuntime().maxMemory()/1024/1024 +"MB");
//        TimeUnit.SECONDS.sleep(10);
//        List<String> list=null;
//        for (String str : list) {
//            System.out.println(str);
//        }

        Boolean.valueOf(true);
        System.out.println(Boolean.valueOf("TT"));

        String time = "2019/10/23";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false); // time parse validation
        try {
            Date timeDate = sdf.parse(time);
            System.out.println(timeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
