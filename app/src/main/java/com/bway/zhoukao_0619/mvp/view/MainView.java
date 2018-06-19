package com.bway.zhoukao_0619.mvp.view;

import com.bway.zhoukao_0619.base.IView;
import com.bway.zhoukao_0619.mvp.model.bean.RegBean;


public interface MainView extends IView {
    void onSuccess(RegBean loginBean);
    void onFaild(String error);
}
