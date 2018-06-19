package com.bway.zhoukao_0619.base;

public abstract class BasePresenter<V extends IView> {
    protected V view;
    public BasePresenter(V view){
        this.view=view;
        initModel();
    }
//解决内存泄漏
    protected abstract void initModel();
    void onDestroy(){
        view=null;
    }
}
