package middlem.person.utilsmodule;

import android.content.Context;
/***********************************************
 *
 * <P> desc:   utils初始化工具，持有外部的context
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:02
 ***********************************************/

public class AppUtilsContextWrapper {

    private static Context sContext;
    private AppUtilsContextWrapper(){}

    /**
     * 在app module的Application中注入context
     * @param context
     */
    public static void init(Context context){
        sContext=context.getApplicationContext();
    }

    /**
     * 获取Context
     * @return Context
     */
    public static Context getContext(){
        if (sContext==null){
            throw new IllegalStateException("should call init method first");
        }
        return sContext;
    }

    /**
     * 传入String id获取到String内容
     * @param resId
     * @return
     */
    public static String getString(int resId){
        return getContext().getString(resId);
    }
}
