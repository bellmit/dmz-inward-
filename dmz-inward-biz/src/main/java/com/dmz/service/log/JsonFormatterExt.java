package com.dmz.service.log;

import ch.qos.logback.contrib.jackson.JacksonJsonFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by dmz on 2016/9/5.
 */
public class JsonFormatterExt extends JacksonJsonFormatter {

    private String module;

    public JsonFormatterExt() {
        this.module = loadModulName();
    }

    @Override
    public String toJsonString(Map m) throws IOException {
        m.put("module", module);
        return super.toJsonString(m);
    }

    private String loadModulName() {
        String appName = null;
        InputStream in = null;

        try {
//              发布到容器中使用
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream("/META-INF/app.properties");
            if (in == null) {
//              JunitTest时候使用
                in = JsonFormatterExt.class.getResourceAsStream("/META-INF/app.properties");
            }

            if (in != null) {
                Properties e = new Properties();
                e.load(in);
                appName = e.getProperty("app.name");
                if (appName == null) {
                    appName = "UNSET";
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception var11) {
                }
            }

        }
        return appName;
    }

}
