package com.hngy.rjxh.artifactforcar;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.hngy.rjxh.artifactforcar.fragment.HomeFragment;
import com.hngy.rjxh.artifactforcar.fragment.ShowDatasFragment;
import com.hngy.rjxh.artifactforcar.model.SensorDataBean;
import com.hngy.rjxh.artifactforcar.presenter.MainPresenter;
import com.hngy.rjxh.artifactforcar.view.IMainView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IMainView{
    private FragmentTransaction mFragmentTransaction;
    private FragmentManager mFragmentManager;
    private HomeFragment homeFragment;
    private ShowDatasFragment showDatasFragment;
    private MainPresenter mPresenter;
    private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.init(MainActivity.this);
        initView();
    }

    private void initView() {
        mFragmentManager = getSupportFragmentManager();//获取到fragment的管理对象
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start://实例化Presenter
                mPresenter = new MainPresenter(this);
                mPresenter.fetch(); break;
        }
    }

    @Override
    public void showLoading() {
        ToastUtils.showLong("正在体检");
    }

    @Override
    public void showData(SensorDataBean sensorDataBean) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        showDatasFragment = new ShowDatasFragment();
        showDatasFragment.setDataBean(sensorDataBean);
        mFragmentTransaction.add(R.id.fl_container,showDatasFragment);
        //提交事务，调用commit方法提交。
        mFragmentTransaction.commit();
    }
}