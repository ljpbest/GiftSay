package ljp.qianfeng.com.giftsay.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class OKHttpTool {
    public static final int OKHTTP_SUCCESS = 1;
    public static final int OKHTTP_FAIL = 0;
    private static OkHttpClient okHttpClient;
    private static CacheControl cacheControl;
    private static ConnectivityManager connectivityManager;
    private static List<Call> list=new ArrayList<>();
    private static void getInstance(){
        if(okHttpClient==null){
            Cache cache=new Cache(Environment.getDownloadCacheDirectory(),5*1024*1024);
            okHttpClient=new OkHttpClient.Builder().cache(cache).build();
        }
    }
    public static void cancelAll(){
        for(int i=0;i<list.size();i++){
            list.get(i).cancel();
        }
        list.clear();
    }
    private static void getCacheContril(){
        if(cacheControl==null) {
            cacheControl = new CacheControl.Builder()
                    .maxAge(3600, TimeUnit.SECONDS) //缓存的有效期为1小时
                    .build();
        }
    }
    private OKHttpTool(){

    }
    private static boolean netWorkIsAviable(Context context){
        connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null){
            return false;
        }
        return networkInfo.isAvailable();
    }

    public static OkHttpHelper init(String url){
        getInstance();
        getCacheContril();
        return new OkHttpHelper(url);
    }
    public static class OkHttpHelper{
        private String path;
        private IHttpCallBack iHttpCallBack;
        private Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                    int what=msg.what;
                switch (what){
                    case OKHTTP_SUCCESS:
                        iHttpCallBack.success(msg.obj.toString());
                        break;
                    case OKHTTP_FAIL:
                        iHttpCallBack.failure(msg.obj.toString());
                        break;
                }

            }
        };
        public OkHttpHelper(String path) {
            this.path = path;
        }
        public OkHttpHelper get(){
            final Request request=new Request.Builder()
                    .url(path)
                    .get()
                    .cacheControl(cacheControl)
                    .build();
            Call call=okHttpClient.newCall(request);
            list.add(call);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message=handler.obtainMessage();
                    message.obj=e.getMessage();
                    message.what=OKHTTP_FAIL;
                    list.remove(call);
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message message=handler.obtainMessage();
                    message.obj=response.body().string();
                    message.what=OKHTTP_SUCCESS;
                    list.remove(call);
                    handler.sendMessage(message);
                }
            });
            return this;
        }

        public OkHttpHelper post(FormBody formBody){
            Request request=new Request.Builder()
                    .url(path)
                    .post(formBody)
                    .cacheControl(cacheControl)
                    .build();
            Call call=okHttpClient.newCall(request);
            list.add(call);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message=handler.obtainMessage();
                    message.obj=e.getMessage();
                    message.what=OKHTTP_FAIL;
                    list.remove(call);
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message message=handler.obtainMessage();
                    message.obj=response.body().string();
                    message.what=OKHTTP_SUCCESS;
                    list.remove(call);
                    handler.sendMessage(message);
                }
            });
            return this;
        }
        public void callBack(IHttpCallBack iHttpCallBack){
            this.iHttpCallBack=iHttpCallBack;
        }
    }
}
