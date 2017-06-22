package dmz.IPAddressUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author dmz
 * @date 2017/2/23
 */
public class IPAddress {

    public static List<String> getLocalInetAddress() {
        ArrayList inetAddressList = new ArrayList();

        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();

            while (e.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) e.nextElement();
                Enumeration addrs = networkInterface.getInetAddresses();

                while (addrs.hasMoreElements()) {
                    inetAddressList.add(((InetAddress) addrs.nextElement()).getHostAddress());
                }
            }

            return inetAddressList;
        } catch (SocketException var4) {
            throw new RuntimeException("get local inet address fail", var4);
        }
    }

    public static String localhostAddress() {
        try {
            InetAddress e = InetAddress.getLocalHost();
            return e.getHostAddress();
        } catch (Throwable var1) {
            return "";
        }
    }

    public static void main(String[] args) {
//        System.out.println(localhostAddress());
        List<String> str = getLocalInetAddress();
        System.out.println();
    }
}
