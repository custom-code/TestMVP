package com.zero.test.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.zero.test.R;
import com.zero.test.app.presenter.LoginPresenter;
import com.zero.test.app.presenter.impl.LoginPresenterImpl;
import com.zero.test.app.view.LoginView;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {
    private Context context;
    private EditText mEtvUser, mEtvPwd;
    private Button mBtnSubmit;
    private ProgressBar mProgressBar;
    private LoginPresenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = LoginActivity.this;
        setContentView(R.layout.login_layout);
        loginPresenter = new LoginPresenterImpl(this);
        setYouMengConfig();
        initView();
    }

    private void initView() {
        mEtvUser = (EditText) findViewById(R.id.login_user);
        mEtvPwd = (EditText) findViewById(R.id.login_pwd);
        mBtnSubmit = (Button) findViewById(R.id.login_submit);
        mProgressBar = (ProgressBar) findViewById(R.id.login_progress);
        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUserNameError() {
        mEtvUser.setError(getString(R.string.login_user_error));
    }

    @Override
    public void setPasswordError() {
        mEtvPwd.setError(getString(R.string.login_pwd_error));
    }

    @Override
    public void jumpToMainActivity() {
        startActivity(new Intent(context, TestActivity.class));
//        startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_submit:
                loginPresenter.validateCredentials(mEtvUser.getText().toString(), mEtvPwd.getText().toString());
                break;
        }
    }

    /**
     * 设置友盟统计的配置参数
     */
    private void setYouMengConfig() {
        MobclickAgent.updateOnlineConfig(this);//配置发送策略，可以在友盟后台配置，默认为启动时发送
        AnalyticsConfig.enableEncrypt(true);//配置是否加密
        MobclickAgent.setSessionContinueMillis(3 * 1000);//配置session时长，在时长内退出重新登陆，不会发送数据，会将本次的数据累计在同一个session中，下一次启动时超过了session时长才会发送
        //MobclickAgent.openActivityDurationTrack(false);//禁止默认的页面统计方式，这样将不会再自动统计Activity，要统计需要添加onPageStart/onPageEnd方法
    }

    private String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
