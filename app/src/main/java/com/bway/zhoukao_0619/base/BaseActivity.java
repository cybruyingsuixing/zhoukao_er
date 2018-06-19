package com.bway.zhoukao_0619.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity{
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        initViews();
        initListener();
        presenter=providePresenter();
        initDatas();
    }

    protected abstract P providePresenter();

    protected abstract int provideLayoutId();

    protected abstract void initDatas();

    protected abstract void initListener();

    protected abstract void initViews();

    //解决内存泄漏
    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
