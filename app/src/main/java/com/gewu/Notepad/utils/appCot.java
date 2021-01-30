package com.gewu.Notepad.utils;

import android.content.Context;
import android.content.SharedPreferences;

public  class appCot {

    /**
     * 设置时间
     */
    public static void savemes(Context context, String f) {
        SharedPreferences preferences = context.getSharedPreferences("m", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("is", f);
        editor.apply();
    }
    /**
     * 读取时间
     */
    public static String getmes(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("m", Context.MODE_PRIVATE);
        String is = preferences.getString("is", "");
        return is;
    }
}
