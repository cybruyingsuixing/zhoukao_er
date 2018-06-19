package com.bway.zhoukao_0619.mvp.presenter;

import com.bway.zhoukao_0619.base.BasePresenter;
import com.bway.zhoukao_0619.mvp.model.RegModel;
import com.bway.zhoukao_0619.mvp.model.bean.RegBean;
import com.bway.zhoukao_0619.mvp.view.MainView;


public class RegPresenter extends BasePresenter<MainView> {


    private RegModel mainModel;

    public RegPresenter(MainView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        mainModel = new RegModel();
    }
    public void reg(String account,String password){
        if (account.equals("")){
            if (view!=null){
                view.onFaild("手机号不能为空");
            }
        }
        mainModel.login(account, password, new RegModel.ILoginModelCallBace() {

            @Override
            public void onMainSuccess(RegBean loginBean) {
                if (view!=null){
                    view.onSuccess(loginBean);
                }
            }

            @Override
            public void onMainFaild(String error) {
                if (view!=null){
                    view.onFaild(error);
                }
            }


        });
    }
}
