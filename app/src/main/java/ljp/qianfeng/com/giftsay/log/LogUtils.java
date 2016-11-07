package ljp.qianfeng.com.giftsay.log;

import android.util.Log;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class LogUtils {
    private static final String TAG="GiftSay_Log";
    private static final boolean ISLOG=true;
    public static void log(Class mclass,String str){
        if(ISLOG){
            Log.i(TAG,mclass.getName()+"===="+str);
        }
    }
}
