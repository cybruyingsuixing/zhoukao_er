package com.bway.zhoukao_0619.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private final Handler mhandler;
    private OkHttpClient client ;

    private OkHttpUtils(){
        mhandler = new Handler(Looper.getMainLooper());
        client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }
    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    return okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }
    public void doPost(String url, Map<String,String> map, final OkCallback okCallback){
        FormBody.Builder builder = new FormBody.Builder();
        for (String key:map.keySet()) {
            builder.add(key,map.get(key));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (okCallback!=null){
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            okCallback.getFailure(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (response!=null && response.isSuccessful()){
                                String s = response.body().string();
                                if (okCallback!=null){
                                    okCallback.getResponse(s);
                                    return;
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if (okCallback!=null){
                            okCallback.getFailure(new Exception("网络异常"));
                        }
                    }
                });
            }
        });
    }
    public interface OkCallback{
        void getFailure(Exception e);
        void getResponse(String josn);
    }

}
