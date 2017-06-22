package dmztest.SecurityExample;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by dmz on 2016/9/2.
 */



import java.util.Date;

/**
 * @author dmz
 * @tag 撤销组合订单
 * @memo 2016/9/8
 */
 class CancelPoOrderResponse implements Serializable {

    private static final long serialVersionUID = -846190868340060582L;
    private Date orderCancelledOn;

    private Boolean isDuplicated;


    public Boolean getIsDuplicated() {
        return isDuplicated;
    }

    public void setIsDuplicated(Boolean duplicated) {
        isDuplicated = duplicated;
    }
}

public class EnDeCoding {
    public static void main(String[] args) {

//        System.out.println(String.format("%02x", 10));
//        System.out.println(10 & 0xFF);
//        System.out.println(Integer.toHexString(10 & 0xff));
//
//        System.out.println(Integer.parseInt("0a",16));
//
//        System.out.println(System.getProperty("line.separator")); //换行符
//
//        Map<String, String> params = new HashMap<>();
//        params.put("012", "330");
//        params.put("112", "440");
//
//        System.out.println(params.toString());
//
//        String pa = JSON.toJSONString(params);
//        System.out.println(pa);



        String str="{\"orderCancelleOn\":\"2016-09-14\",\"isDuplicated\":true}";
        CancelPoOrderResponse response = JSON.parseObject(str, CancelPoOrderResponse.class);
        System.out.println(JSON.toJSONString(response));
    }
}
