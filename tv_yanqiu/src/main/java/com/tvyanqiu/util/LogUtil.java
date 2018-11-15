package com.tvyanqiu.util;

import android.util.Log;

import com.tvyanqiu.config.Constant;

public class LogUtil {

    public static boolean ISDEBUG = Constant.LOG_ISDEBUG;

    public static void v(String context, String content) {
        if (ISDEBUG)
            Log.v(context, content);
    }

    public static void d(String context, String content) {
        if (ISDEBUG) {
            //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
            //  把4*1024的MAX字节打印长度改为2001字符数
            int max_str_length = 2001 - context.length();
            //大于4000时
            while (content.length() > max_str_length) {
                Log.d(context, content.substring(0, max_str_length));
                content = content.substring(max_str_length);
            }
            //剩余部分
            Log.d(context, content);
        }
    }

    public static void w(String context, String content) {
        if (ISDEBUG)
            Log.w(context, content);
    }

    public static void i(String context, String content) {
        if (ISDEBUG)
            Log.i(context, content);
    }

    public static void e(String context, String content) {
        if (ISDEBUG)
            Log.e(context, content);
    }
}
