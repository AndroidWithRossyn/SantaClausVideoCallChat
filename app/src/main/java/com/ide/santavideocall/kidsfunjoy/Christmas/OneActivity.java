package com.ide.santavideocall.kidsfunjoy.Christmas;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.ide.santavideocall.kidsfunjoy.R;
import com.pesonal.adsdk.AppManage;

public class OneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        AppManage.getInstance(OneActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(OneActivity.this).showInterstitialAd(OneActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                OneActivity.super.onBackPressed();
                overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }
}