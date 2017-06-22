package com.dmz.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dmz
 * @date 2017/2/14
 */
public class IdCardValidation {

    // 地区编码
    public static final Map<String, String> REGION_CODE = new HashMap<String, String>();

    static {
        REGION_CODE.put("11", "北京");
        REGION_CODE.put("12", "天津");
        REGION_CODE.put("13", "河北");
        REGION_CODE.put("14", "山西");
        REGION_CODE.put("15", "内蒙古");
        REGION_CODE.put("21", "辽宁");
        REGION_CODE.put("22", "吉林");
        REGION_CODE.put("23", "黑龙江");
        REGION_CODE.put("31", "上海");
        REGION_CODE.put("32", "江苏");
        REGION_CODE.put("33", "浙江");
        REGION_CODE.put("34", "安徽");
        REGION_CODE.put("35", "福建");
        REGION_CODE.put("36", "江西");
        REGION_CODE.put("37", "山东");
        REGION_CODE.put("41", "河南");
        REGION_CODE.put("42", "湖北");
        REGION_CODE.put("43", "湖南");
        REGION_CODE.put("44", "广东");
        REGION_CODE.put("45", "广西");
        REGION_CODE.put("46", "海南");
        REGION_CODE.put("50", "重庆");
        REGION_CODE.put("51", "四川");
        REGION_CODE.put("52", "贵州");
        REGION_CODE.put("53", "云南");
        REGION_CODE.put("54", "西藏");
        REGION_CODE.put("61", "陕西");
        REGION_CODE.put("62", "甘肃");
        REGION_CODE.put("63", "青海");
        REGION_CODE.put("64", "宁夏");
        REGION_CODE.put("65", "新疆");
        REGION_CODE.put("71", "台湾");
        REGION_CODE.put("81", "香港");
        REGION_CODE.put("82", "澳门");
        REGION_CODE.put("91", "国外");
    }

    /**
     * 18位身份证前17位的权重值
     */
    protected static final int[] WEIGHT = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 校验码可取值
     */
    protected static final String[] CHECK_CODE = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    /**
     * 开始身份证号验证
     *
     * @param idString
     * @return
     * @occurs required
     */
    public static boolean validate(String idString) {

        // 验证空
        if (idString == null || idString.isEmpty()) {
            return Boolean.FALSE;
        }

        // 长度校验
        if (idString.length() != 15 && idString.length() != 18) {
            return Boolean.FALSE;
        }

        if (idString.length() == 15) {
            // 15位身份证号
            if (isDigital(idString)) {
                // 15位转成18位
                idString = convert15To18(idString);

                if (idString == null) {
                    return Boolean.FALSE;
                }

            } else {
                return Boolean.FALSE;
            }
        }

        if (idString.length() == 18) {
            if (isDigital(idString.substring(0, 17)) == false) {
                return Boolean.FALSE;
            }
        }

        // 验证地区码，出生日期，校验位
        if (isEffectiveRegionCode(idString) && isEffectiveBirthday(idString) && isValidCheckCode(idString)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * @param idString
     * @return
     * @brief 是否已经成年
     * @occurs required
     */
    public static boolean isAdult(String idString) {
        if (idString.length() == 15) {
            // 15位身份证
            return true;
        }

        if (idString.length() == 18) {
            String strYear = idString.substring(6, 10);// 年
            String strMonth = idString.substring(10, 12);// 月
            String strDay = idString.substring(12, 14);// 日

            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            if (year - Integer.parseInt(strYear) < 18) {
                // 年份 差小于18，未成年
                return false;
            } else if (year - Integer.parseInt(strYear) == 18) {
                // 年份差等于18，则比较月份
                int month = now.get(Calendar.MONTH); // 从0开始计数
                if (month + 1 < Integer.parseInt(strMonth)) {
                    // 当前月份小于出生月份，未成年
                    return false;
                } else if (month + 1 == Integer.parseInt(strMonth)) {
                    // 月份也相等，则比较日期
                    int day = now.get(Calendar.DAY_OF_MONTH);
                    if (day <= Integer.parseInt(strDay)) {
                        // 年份等于18，月份相等，当前日期小于等于出生日期，未成年
                        return false;
                    }
                }
            }

            return true;
        }

        // 非15位，非18位
        return false;
    }

    /**
     * 校验身份证号码最后一位
     */
    private static boolean isValidCheckCode(String idString) {
        // 算最后一位校验码
        int checkSum = 0;
        for (int i = 0; i < 17; i++) {
            int ai = Integer.parseInt(String.valueOf(idString.charAt(i)));
            checkSum = checkSum + ai * WEIGHT[i];
        }

        int checkNum = checkSum % 11;
        String checkChar = CHECK_CODE[checkNum].toUpperCase(Locale.CHINA);

        return idString.toUpperCase(Locale.CHINA).endsWith(checkChar) ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 检查出生日期
     */
    private static boolean isEffectiveBirthday(String idString) {
        String strYear = idString.substring(6, 10);// 年
        String strMonth = idString.substring(10, 12);// 月
        String strDay = idString.substring(12, 14);// 日

        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {

            return Boolean.FALSE;
        }
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if ((calendar.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (calendar.getTime().getTime()
                    - dateFormat.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {

                return Boolean.FALSE;
            }
        } catch (NumberFormatException e) {
            return Boolean.FALSE;
        } catch (ParseException e) {
            return Boolean.FALSE;
        }

        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {

            return Boolean.FALSE;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {

            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * 是否是日期
     */
    private static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);

        return m.matches() ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 检查地区码
     */
    private static boolean isEffectiveRegionCode(String idString) {
        String regionCode = idString.substring(0, 2);

        return REGION_CODE.get(regionCode) == null ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * 判断字符串是否为数字
     */
    private static boolean isDigital(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isDigital = pattern.matcher(str);

        return isDigital.matches() ? true : false;
    }

    /**
     * 将15位的身份证转成18位身份证
     * <p>
     * 15位身份证号码各位的含义: 1-2位省、自治区、直辖市代码； 3-4位地级市、盟、自治州代码； 5-6位县、县级市、区代码；
     * 7-12位出生年月日，比如670401代表1967年4月1日，与18位的第一个区别； 13-15位为顺序号，其中15位单数为男，双数为女；
     */
    private static String convert15To18(String idCard) {

        // 获取出生年月日
        String birthdayStr = idCard.substring(6, 12);
        Date birthdate = null;
        try {
            birthdate = new SimpleDateFormat("yyMMdd").parse(birthdayStr);
        } catch (ParseException e) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthdate);
        String year = String.valueOf(calendar.get(Calendar.YEAR));

        String idCard17 = idCard.substring(0, 6) + year + idCard.substring(8);

        // 算最后一位校验码
        int checkSum = 0;
        for (int i = 0; i < 17; i++) {
            int ai = Integer.parseInt(String.valueOf(idCard17.charAt(i)));
            checkSum = checkSum + ai * WEIGHT[i];
        }

        int checkNum = checkSum % 11;
        String checkChar = CHECK_CODE[checkNum];

        return idCard17 + checkChar;
    }
}
