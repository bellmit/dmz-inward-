package com.dmz.web;

import io.netty.handler.codec.http2.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;

/**
 * @author dmz
 * @date 2017/5/23
 */
public class RequestHandle {
    private static final String HEADER_XFF = "X-Forwarded-For";

    private static final String BUNDLE_VERSION = "bundle_version";
    private static final String BUNDLE_VERSION_UNKNOWN = "0.0.0";
    private static final String DEVICE_INFO = "device_info";
    private static final String DEVICE_INFO_UNKNOWN = "";

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static String getOriginRequestIp(HttpServletRequest request) throws UnknownHostException {
        String ip = request.getHeader(HEADER_XFF);
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                    if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getRemoteAddr(); //service request ip 直接网络交互的IP
                    }
                }
            }
        } else {
            int index = ip.indexOf(",");
            if (index != -1) {
                ip = ip.substring(0, index);
            }
        }

        if ("127.0.0.1".equals(ip)) {
            InetAddress localhost = InetAddress.getLocalHost();
            ip = localhost.getHostAddress();
        }
        return ip;
    }

    public static String getBundleVersion(HttpServletRequest request) {
        String bundleVersion = request.getHeader(BUNDLE_VERSION);
        if (StringUtils.isEmpty(bundleVersion)) {
            bundleVersion = BUNDLE_VERSION_UNKNOWN;
        }
        return bundleVersion;
    }

    public static String getDeviceInfo(HttpServletRequest request) {
        String deviceInfo = request.getHeader(DEVICE_INFO);
        if (StringUtils.isEmpty(deviceInfo)) {
            deviceInfo = DEVICE_INFO_UNKNOWN;
        } else {
            try {
                deviceInfo = URLDecoder.decode(deviceInfo, "utf-8");
                StringBuffer buf = new StringBuffer();
                for (int i = 0; i < deviceInfo.length(); i++) {
                    if (!isEmojiCharacter(deviceInfo.charAt(i))) {
                        buf.append(deviceInfo.charAt(i));
                    }
                }
                deviceInfo = buf.toString();
            } catch (UnsupportedEncodingException e) {
                LOGGER.info("unsupported encoding while decode http request device info");
            }
        }
        return deviceInfo;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    /**
     * @param hexString
     * @return
     * @brief Hex字符串转字节数组
     * @occurs required
     */
    public static byte[] fromHex(String hexString) {
        if (hexString == null) {
            return null;
        }

        byte[] result = new byte[hexString.length() / 2];
        char[] charArray = hexString.toCharArray();
        for (int i = 0, c = 0; i < charArray.length; i += 2, c++) {
            result[c] = (byte) (Integer.parseInt(new String(charArray, i, 2), 16));
        }

        return result;
    }

    /**
     * @param bytes
     * @return
     * @brief 字节数组转Hex字符串
     * @occurs required
     */
    public static String asHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(0x00FF & b);
            hexStringBuilder.append(hexString.length() == 1 ? "0" + hexString : hexString);
        }

        return hexStringBuilder.toString();
    }

}
