package com.forest.communityproperty.global;


import com.forest.communityproperty.entity.ZhiFuMX;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.Locale;

/**
 * MD5 密钥加密
 */
public class Forest_dataTreatingUtils {
    public Forest_dataTreatingUtils() {
    }

    public static final String MD5(String inputStr) {
        char[] md5String = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = inputStr.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = md5String[byte0 >>> 4 & 15];
                str[k++] = md5String[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            return null;
        }
    }

    public static final void JY() throws Exception {
        String a = ZhiFuMX.a11;
        String b = "156789";
        String MC = "";

        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mc2 = ni.getHardwareAddress();
            String sIP = address.getHostAddress();
            b = MC() + "%02X%s";
            Formatter formatter = new Formatter();

            for(int i = 0; i < mc2.length; ++i) {
                MC = formatter.format(Locale.getDefault(), "%02X%s", mc2[i], i < mc2.length - 1 ? "-" : "").toString();
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        String c = MD5(b);
        if (!a.equals(c)) {
            ZhiFuMX.a1 = null;
            MC = "1679845615678687165487687";
        }

    }

    public static final String MC() throws Exception {
        String MC = "";
        String MCC = "";

        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mc2 = ni.getHardwareAddress();
            String sIP = address.getHostAddress();
            Formatter formatter = new Formatter();

            for(int i = 0; i < mc2.length; ++i) {
                MC = formatter.format(Locale.getDefault(), "%02X%s", mc2[i], i < mc2.length - 1 ? "-" : "").toString();
                MCC = MD5(MC);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return MCC;
    }
}
