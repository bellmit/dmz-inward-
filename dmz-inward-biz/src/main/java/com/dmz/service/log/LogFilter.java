package com.dmz.service.log;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author dmz
 * @tag 日志过滤
 * @memo 2016/10/27
 */
public class LogFilter {
    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    private static final Map<MaskPosition, List<String>> MASK_WORDS = new HashMap<>();

    private final static List<String> EXCLUDE_MASK_WORDS = new ArrayList<>();

    static {

        List<String> phones = new ArrayList<>();
        phones.add("PHONE");

        List<String> idCards = new ArrayList<>();
        idCards.add("IDENTITYNO");

        List<String> names = new ArrayList<>();
        names.add("REALNAME");


        List<String> cardNos = new ArrayList<>();
        cardNos.add("BANKNO");
        cardNos.add("PAYMENTNO");

        MASK_WORDS.put(new MaskPosition(0, 13), cardNos);
        MASK_WORDS.put(new MaskPosition(0, 1), names);
        MASK_WORDS.put(new MaskPosition(6, 14), idCards);
        MASK_WORDS.put(new MaskPosition(3, 7), phones);

        EXCLUDE_MASK_WORDS.add("EMAIL");
        EXCLUDE_MASK_WORDS.add("PASSWORD");

    }

    public static String toFilterString(Object object) {

        if (object == null) {
            return null;
        }
        Field[] fields = object.getClass().getDeclaredFields();
        ReflectionToStringBuilder refBuilder = new ReflectionToStringBuilder(object, ToStringStyle.SHORT_PREFIX_STYLE) {
            protected boolean accept(Field f) {
                return super.accept(f)
                        && !f.getName().toUpperCase().equals("PASSWORD")
                        && !f.getName().toUpperCase().equals("EMAIL");
            }
        };
        Map<String, MaskPosition> maskFieldMap = new HashMap<>();
        List<String> excludeFields = new ArrayList<>();

        for (final Field field : fields) {

            LogMask logMask = field.getAnnotation(LogMask.class);
            if (logMask != null) {
                MASK_WORDS.put(new MaskPosition(logMask.maskStart(), logMask.maskEnd()), new ArrayList<String>(){
                    {
                        add(field.getName());
                    }
                });
            }
            // mask field
            for (Map.Entry<MaskPosition, List<String>> entry : MASK_WORDS.entrySet()) {
                for (String keys : entry.getValue()) {
                    if (field.getName().toUpperCase().equals(keys)) {
                        maskFieldMap.put(field.getName(), entry.getKey());
                        break;
                    }
                }
            }
            // exclude field
            for (String exclude : EXCLUDE_MASK_WORDS) {
                if (field.getName().toUpperCase().equals(exclude)) {
                    excludeFields.add(field.getName());
                    break;
                }
            }

        }

        Set<String> maskFields = maskFieldMap.keySet();

        refBuilder.setExcludeFieldNames(maskFields.toArray(new String[maskFields.size()]));
        appendMaskFields(refBuilder, object, maskFieldMap);


        return refBuilder.toString();
    }

    private static void appendMaskFields(ReflectionToStringBuilder builder, Object object, Map<String, MaskPosition> maskFieldMap) {
        try {
            for (String fieldName : maskFieldMap.keySet()) {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(object);

                MaskPosition maskPosition = maskFieldMap.get(fieldName);

                builder.append(fieldName, maskPosition.filter(fieldValue));
            }
        } catch (IllegalAccessException e) {
            logger.info("appendMaskFields failed", e);
        } catch (NoSuchFieldException e) {
            logger.info("appendMaskFields failed", e);
        }
    }
}
