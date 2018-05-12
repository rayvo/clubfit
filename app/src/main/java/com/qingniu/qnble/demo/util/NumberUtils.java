package com.qingniu.qnble.demo.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 数值工具，包括数值的格式化，取精度等
 *
 * @author hdr
 */
public class NumberUtils {
    public final static double hUnit = 2.54; // 遵循四舍五入

    private static final DecimalFormat COMMON_FORMATER = new DecimalFormat("0.0");
    private static final DecimalFormat COMMON_FORMAT2 = new DecimalFormat("0.00");

    /**
     * 把小数转为只有一位小数的输出
     *
     * @param value
     * @return
     */
    public static String format(float value) {
        //这种方案会导致，小数点在有些类型的语言下为逗号
//        return String.format("%.1f",value);
//        return COMMON_FORMATER.format(value);

        String v = value + "";
        return v.substring(0, v.indexOf(".") + 2);
        //这种方案是有舍入的
//        return new BigDecimal(String.valueOf(f)).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue() + "";
    }

    /**
     * 把小数转为两位小数的输出
     *
     * @param value
     * @return
     */
    public static String format2(float value) {
//        return COMMON_FORMAT2.format(value);
        String v = value + "";
        int pos = v.indexOf(".");
        if (v.length() == pos + 2) {//只有一位小数
            return v + "0";
        } else return v.substring(0, pos + 3);
//        return new BigDecimal(String.valueOf(f)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() + "";
    }

    /**
     * @param f
     * @param scale 保留的位数
     * @return
     */
    public static float getPrecision(float f, int scale) {
        return new BigDecimal(String.valueOf(f)).setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();

/*        if (scale < 0) {
            throw new IllegalArgumentException("需要保留的位数不能小于零");
        }

        BigDecimal bigDecimalOne = new BigDecimal(Float.toString(f));
        BigDecimal bigDecimalTwo = new BigDecimal("1");

        return bigDecimalOne.divide(bigDecimalTwo, scale,
                BigDecimal.ROUND_HALF_UP).floatValue();*/

    }

    /**
     * 四舍五入
     *
     * @param f
     * @return
     */
    public static float getOnePrecision(float f) {
        return getOnePrecision((double) f);
    }

    public static float getOnePrecision(double f) {
        float value;
        try {
            value = new BigDecimal(String.valueOf(f)).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        } catch (NumberFormatException e) {
            value = 0;
        }
        return value;
    }

    public static float getTwoPrecision(double d) {
        return new BigDecimal(String.valueOf(d)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 格式化整数到2位，不足的补0
     *
     * @param value
     * @return
     */
    public static String formatIntegerTo2(int value) {
        if (value < 10) {
            return "0" + value;
        } else
            return String.valueOf(value);
    }

    /**
     * 生成验证码
     *
     * @return
     */
    public static String getRandomNumberString() {
        StringBuilder builder = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < 4; i++) {
            builder.append(Math.abs(ran.nextInt() % 10));
        }
        return builder.toString();

    }

    /**
     * 把所有包含空格的字符串都给替换掉
     *
     * @param resource
     * @param ch
     * @return
     */
    public static String removeAllSpace(String resource, char ch) {
        StringBuffer buffer = new StringBuffer();
        int position = 0;
        char currentChar;
        while (position < resource.length()) {
            currentChar = resource.charAt(position++);
            if (currentChar != ch) {
                buffer.append(currentChar);
            }
        }
        return buffer.toString();
    }

    public static String[] splitString(float score) {
        String[] scoreInfo = (score + "").split("\\.");
        scoreInfo[1] = "." + scoreInfo[1] + "分";
        return scoreInfo;

    }

    public static String[] splitShape(String info) {
        String[] infos = (info + "").split("\\-");
        return infos;
    }

    public static String[] splitHealth(String info) {
        String[] infos = info.split("\\" + info.substring(1, 2) + "");
        return infos;

    }


    public static char[] getInput() {
        char[] c = new char[70];
        int[] a = new int[26];
        for (int i = 0; i < a.length; i++) {
            a[i] = 'a' + i;
            c[i] = (char) a[i];
        }

        char[] d = new char[26];
        int[] b = new int[26];
        for (int i = 0; i < b.length; i++) {
            b[i] = 'A' + i;
            d[i] = (char) b[i];
        }

        System.arraycopy(d, 0, c, 26, d.length);

        char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '@', '.', '-', '_', ','};

        System.arraycopy(number, 0, c, 52, number.length);
        return c;

    }

    /**
     * 千克转磅
     * lb = ((((kg*100) * 11023 + 50000)/100000)<<1) / 10
     */

    public static float kgToLb(float kg) {
        float temp = NumberUtils.getTwoPrecision(kg * 100);
        return getTwoPrecision((double) ((int) ((temp * 11023 + 50000) / 100000) << 1) / 10f);
    }

    /**
     *
     * @param kg
     * @return  传入尺子界面的方法
     * 这个方法只在设定目标界面初始化目标值时当前单位为lb使用
     */
    public static float kgToLbForSetGoal(float kg) {
        return getTwoPrecision(kg*2.2046226218488d);
    }

    /**
     *
     * @param kg
     * @return  测量界面目标值计算方法
     * 1.这个方法一个是测量界面目标值转化时使用
     * 2.另外一个是在尺子界面中尺子控件(RulerView)当前单位是lb时计算最大最小时使用
     * 3.还有一个地方是设定目标时初始化尺子中,判断用户目标值是否大于尺子最大,小于最小时使用
     */
    public static float kgToLbForTransformGoal(float kg) {
        return getOnePrecision(kg*2.2046226218488d);
    }

    /**
     * 千克转英石，如果<14磅，就显示磅，>=14磅，显示英石
     *
     * @return
     */
    public static float kgToStValue(float kg) {
        return getOnePrecision(kgToLb(kg) / 14f);
    }

    /**
     * 千克转英石,两位小数，如果<14磅，就显示磅，>=14磅，显示英石
     *
     * @return
     */
    public static float kgToStValueTwoPrecision(float kg) {
        return getTwoPrecision(kgToLb(kg) / 14f);
    }



    /**
     * 英石转kg
     *
     * @param st
     * @return
     */
    public static float stToKg(float st) {
        return lbToKg(NumberUtils.getOnePrecision(st * 14));
    }

    /**
     * 千克转磅,不进行四舍五入
     */
    public static float kgToLbOther(float kg) {
        return (kg * 11023 / 10000) * 2;
    }

    /**
     * 磅转成kg
     */
    public static float lbToKg(float lb) {
        return getTwoPrecision((double) (lb * 10000 / 11023 / 2));
    }


    /**
     * 厘米 转成 英寸(四舍五入取整)
     */
    public static int cmToInch(float cm) {
        return (int) Math.round(cm / hUnit);
    }

    /**
     * 英尺 转成厘米
     */
    public static float inchToCm(float in) {
        return getOnePrecision(in * hUnit);
    }

    /**
     * 将 ""'""转成cm
     */
    public static float ftToCm(int h, int in) {
        return getTwoPrecision((h * 12 + in) * hUnit);
    }

    /**
     * 将 cm转成""'""
     */
    public static int[] cmToFt(int cm) {
        int[] values = new int[2];
        int in = cmToInch(cm);
        values[0] = (int) in / 12;
        values[1] = (int) (in - values[0] * 12);
        return values;
    }

    /**
     * 将cm转换成多少英尺多少英寸
     */
    public static String cmToFtStr(int cm) {
        int[] values = NumberUtils.cmToFt(cm);
        return values[0] + "'" + values[1] + "\"";
    }

    /**
     * 将 英寸转成""'""
     */
    public static String ftTo(int in) {
        return in / 12 + "'" + in % 12 + "\"";
    }

    /**
     * 将英寸转成 ""'"",输出数组
     */
    public static int[] inchToFt(int in) {
        int[] values = new int[2];
        values[0] = in / 12;
        values[1] = in % 12;
        return values;
    }

}
