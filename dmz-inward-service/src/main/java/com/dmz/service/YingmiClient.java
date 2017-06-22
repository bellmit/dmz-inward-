package com.dmz.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Type;
import java.security.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author dmz
 * @tag
 * @memo 2016/6/2
 */
public class YingmiClient extends HttpsClient implements InitializingBean , DisposableBean {

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    private String apiKey = "b3697ad6-9e53-47a1-a223-8913e775db09";

    private String apiSecret = "K2P0nxLZD8VpSfBf4GPcxs7WaUHkLcjh";

    private String host = "api-test.frontnode.net";

    private String basePath = "/v1";

    private String keyAlgorithm = "HmacSHA1";

    private String getSig(String method, String path, String apiSecret, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        Set<String> keySet = new TreeSet<>();

        if (params != null && !params.isEmpty() ) {
            // params require sorting by name !!!
            keySet = new TreeSet<>(params.keySet());
        }
        for (String key : keySet) {
            String value = params.get(key);
            if (value == null) {
                continue;
            }
            sb.append(key);
            sb.append("=");
            sb.append(params.get(key));
            sb.append("&");
        }
        sb.setLength(sb.length() - 1); // trim the last "&"
        String unifiedString = method.toUpperCase() + ":" + path + ":" + sb.toString();

        // calc hmac sha1
        try {
            SecretKeySpec secret = new SecretKeySpec(apiSecret.getBytes(), keyAlgorithm);
            Mac mac = Mac.getInstance(keyAlgorithm);
            mac.init(secret);
            byte[] hmac = mac.doFinal(unifiedString.getBytes()); // UTF8

            // base64 encode the hmac
            String sig = new BASE64Encoder().encode(hmac);
//            logger.debug("signature: " + sig);
            return sig;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T get(String path, Map<String, String> params,Type type) {
        params.put("key", apiKey);
        params.put("sigVer", "1");
        String ts = format.format(new Date());
        params.put("ts", ts);
        params.put("nonce", RandomStringUtils.randomAlphanumeric(16));
        String sig = getSig("GET", path, apiSecret, params);
        params.put("sig", sig);
        return get(host, basePath + path, params,type);
    }

    public void afterPropertiesSet() throws Exception {
        initHttpsClient();
    }

    public void destroy() throws Exception {
        httpClient.close();
        shutdown();
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

}
