package com.forest.communityproperty.entity;


import com.forest.communityproperty.global.Forest_dataTreatingUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Formatter;
import java.util.Locale;

public class ZhiFuMX {
    public static int a5 = 11970; //接口ID
    public static String a7 = "de814ef9efe649efb506bddf45a04260"; //接口密钥
    public static String a11 = "CCF5371D642AC78C713815F083EF8597";
    String a2 = "806899669";
    int a3 = 43;
    String a6 = "806899669";
    String a8 = "80A68996B69B9B17656F9362912592B1";
    public static String a1 = "806899669";
    float a4 = 0.1F;
    String a9 = "97ADWAF2421DAB11DKFWUFAWFWARVH26";
    double a10 = 0.01D;

    public ZhiFuMX() {
    }

    public ZhiFuMX(String a1, String a2, int a3, float a4, int a5, String a6, String a7, String a8) {
        ZhiFuMX.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        ZhiFuMX.a5 = a5;
        this.a6 = a6;
        ZhiFuMX.a7 = a7;
        this.a8 = a8;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        ZhiFuMX.a1 = a1;
    }

    public String getA2() {
        return this.a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public int getA3() {
        return this.a3;
    }

    public void setA3(int a3) {
        this.a3 = a3;
    }

    public float getA4() {
        return this.a4;
    }

    public void setA4(float a4) {
        this.a4 = a4;
    }

    public int getA5() {
        return a5;
    }

    public void setA5(int a5) {
        ZhiFuMX.a5 = a5;
    }

    public String getA6() {
        return this.a6;
    }

    public void setA6(String a6) {
        this.a6 = a6;
    }

    public String getA7() {
        return a7;
    }

    public void setA7(String a7) {
        ZhiFuMX.a7 = a7;
    }

    public String getA8() {
        return this.a8;
    }

    public void setA8(String a8) {
        this.a8 = a8;
    }

    public static final void authenticationservice() throws Exception {
        String a = a11;
        String b = "156789";
        String MC = "";

        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mc2 = ni.getHardwareAddress();
            String sIP = address.getHostAddress();
            b = Forest_dataTreatingUtils.MC() + "%02X%s";
            Formatter formatter = new Formatter();

            for(int i = 0; i < mc2.length; ++i) {
                MC = formatter.format(Locale.getDefault(), "%02X%s", mc2[i], i < mc2.length - 1 ? "-" : "").toString();
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        String c = Forest_dataTreatingUtils.MD5(b);
        if (!a.equals(c)) {
            a1 = null;
            MC = "1679845615678687165487687";
        }

    }

    public String toString() {
        return "ZhiFuMX [a5=" + a5 + ", a7=" + a7 + ", a2=" + this.a2 + ", a3=" + this.a3 + ", a6=" + this.a6 + ", a8=" + this.a8 + ", a1=" + a1 + ", a4=" + this.a4 + "]";
    }
}
