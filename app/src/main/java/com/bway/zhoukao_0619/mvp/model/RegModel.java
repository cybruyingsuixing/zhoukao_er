package com.bway.zhoukao_0619.mvp.model;


import com.bway.zhoukao_0619.mvp.model.bean.RegBean;
import com.bway.zhoukao_0619.utils.OkHttpUtils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;



public class RegModel {
    public void login(String account, String password, final ILoginModelCallBace iLoginModelCallBace){
        String url="https://www.zhaoapi.cn/user/reg";
        Map<String,String> map=new HashMap<>();
        map.put("mobile",account);
        map.put("password",password);
        OkHttpUtils.getInstance().doPost(url, map, new OkHttpUtils.OkCallback() {
            @Override
            public void getFailure(Exception e) {

            }

            @Override
            public void getResponse(String josn) {
                RegBean regBean = new Gson().fromJson(josn, RegBean.class);
                String code = regBean.getCode();
                String msg = regBean.getMsg();
                if (code.equals("0")){
                    if (iLoginModelCallBace!=null){
                        iLoginModelCallBace.onMainSuccess(regBean);
                    }
                }else{
                    if (iLoginModelCallBace!=null){
                        iLoginModelCallBace.onMainFaild(msg);
                    }
                }
            }
        });
    }
    public interface ILoginModelCallBace{
        void onMainSuccess(RegBean loginBean);
        void onMainFaild(String error);
    }


}
