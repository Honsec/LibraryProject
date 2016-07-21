package genius.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Hongsec on 2016-07-21.
 */
public class UtilsLog  {
    
    private static  final String tag_default="Default_Log";

    private static boolean _IS_LOG =true;

    public static void v( String msg) {
        if(_IS_LOG)
        v("", msg);
    }
    public static void i(String msg) {
        if(_IS_LOG)
        i("", msg);
    }
    public static void e(String msg) {
        if(_IS_LOG)
        e("", msg);
    }
    public static void d(String msg) {
        if(_IS_LOG)
        d("", msg);
    }
    public static void w(String msg) {
        if(_IS_LOG)
        w("", msg);
    }

    public static void v(String category, String msg) {
        if(_IS_LOG)
        v(tag_default, category, msg);
    }
    public static void i(String category, String msg) {
        if(_IS_LOG)
        i(tag_default, category, msg);
    }
    public static void e(String category, String msg) {
        if(_IS_LOG)
        e(tag_default, category, msg);
    }
    public static void d(String category, String msg) {
        if(_IS_LOG)
        d(tag_default, category, msg);
    }
    public static void w(String category, String msg) {
        if(_IS_LOG)
        w(tag_default, category, msg);
    }

    //black
    public static void v(String tag,String category, String msg) {
        if(_IS_LOG)
        Log.v(TextUtils.isEmpty(tag) ? tag_default : tag, (TextUtils.isEmpty(category) ? "" : ("[" + category + "] ")) + msg);
    }

    //black
    public static void i(String tag,String category, String msg) {
        if(_IS_LOG)
        Log.v(TextUtils.isEmpty(tag) ? tag_default : tag, (TextUtils.isEmpty(category) ? "" : ("[" + category + "] ")) + msg);
    }

    //red
    public static void e(String tag,String category, String msg) {
        if(_IS_LOG)
        Log.v(TextUtils.isEmpty(tag) ? tag_default : tag, (TextUtils.isEmpty(category) ? "" : ("[" + category + "] ")) + msg);
    }

    //black
    public static void d(String tag,String category, String msg) {
        if(_IS_LOG)
        Log.v(TextUtils.isEmpty(tag) ? tag_default : tag, (TextUtils.isEmpty(category) ? "" : ("[" + category + "] ")) + msg);
    }

    //blue
    public static void w(String tag,String category, String msg) {
        if(_IS_LOG)
        Log.v(TextUtils.isEmpty(tag) ? tag_default : tag, (TextUtils.isEmpty(category) ? "" : ("[" + category + "] ")) + msg);
    }



}
