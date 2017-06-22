package dmztest.CommonLang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dmz on 2016/9/28.
 */

enum CertificateType {

    //身份证
    ID_CARD("0"),

    //护照
    PASSPORT("1"),

    //军官证
    OFFICERS("2"),

    //士兵证
    SOLDBUCH("3"),

    //港澳通行证
    HKMPASSPORT("4"),

    //户口本
    HOUSEHOLD("5"),

    //外国护照
    FPASSPORT("6"),

    //其他
    OTHERS("7"),

    //文职证
    CIVILIAN("8"),

    //台胞证
    MTPS("9"),

    //警官证
    CROPS("A");


    private String certificateType;

    private CertificateType(String certificateType)
    {
        this.certificateType = certificateType;
    }

    public String getCertificateType()
    {
        return this.certificateType;
    }


    public static CertificateType mapByValue(String value) {
        for(CertificateType prop : values()){
            if(prop.getCertificateType().equals(value)){
                return prop;
            }
        }
        return null;
    }

}

class A{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class B{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BB**BB";
    }
}
public class ReplaceStars {
    private static CertificateType certificateType = CertificateType.CIVILIAN;
    public static void main(String[] args) {
        Map<String, Object> desc = new HashMap<>();
        desc.put("realName", new StringBuffer("殷明").replace(0,1,"*"));
        desc.put("identityType", "1");
        desc.put("identityNo", new StringBuffer("320721199001250059").replace(6, 14, "********"));
        desc.put("paymentNO", new StringBuffer("6212264301012278220").replace(0, 13, "*************"));
        desc.put("phone", new StringBuffer("18362095922").replace(3, 7, "****"));
        desc.put("verifyCode", new StringBuffer("123456").replace(0, 3, "***"));
        desc.put("paymentType", "bank:002");
        desc.put("riskGrade", "0");
        desc.put("memberNo", "dmzvsdmz");
        desc.put("certificateType", certificateType.getCertificateType());
        System.out.println(JSON.toJSONString(desc, SerializerFeature.WriteMapNullValue));
        List<String> list = new ArrayList<>();
//        list.add(null);
//        list.add(null);
        System.out.println(JSON.toJSONString(list, SerializerFeature.WriteMapNullValue));
    }
}
