package dmztest;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dmz on 2016/8/3.
 */
public class DateSqlDateTest implements Serializable {
    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println(date.getTime());
//        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
//        System.out.println(sqlDate.getTime());
//
//        java.sql.Date formateDate = java.sql.Date.valueOf("yyyyMMdd");
//        System.out.println(formateDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss");
        String dataStr = sdf.format(new Date());
        System.out.println(dataStr);
        List<String> list2 = new ArrayList<>();
        List<String> list = null;
        list2.addAll(list);
//        for (String str : list) {
//            System.out.println(str);
//        }
    }
}
