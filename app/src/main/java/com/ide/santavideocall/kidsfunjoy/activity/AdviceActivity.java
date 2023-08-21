package com.ide.santavideocall.kidsfunjoy.activity;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ide.santavideocall.kidsfunjoy.Christmas.FourActivity;
import com.ide.santavideocall.kidsfunjoy.Christmas.OneActivity;
import com.ide.santavideocall.kidsfunjoy.Christmas.ThreeActivity;
import com.ide.santavideocall.kidsfunjoy.Christmas.TwoActivity;
import com.ide.santavideocall.kidsfunjoy.R;
import com.pesonal.adsdk.AppManage;

public class AdviceActivity extends AppCompatActivity {
    ImageView one,two,three,four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        AppManage.getInstance(AdviceActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(AdviceActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(AdviceActivity.this).showInterstitialAd(AdviceActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(AdviceActivity.this, OneActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(AdviceActivity.this).showInterstitialAd(AdviceActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(AdviceActivity.this, TwoActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(AdviceActivity.this).showInterstitialAd(AdviceActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(AdviceActivity.this, ThreeActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(AdviceActivity.this).showInterstitialAd(AdviceActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(AdviceActivity.this, FourActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(AdviceActivity.this).showInterstitialAd(AdviceActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                AdviceActivity.super.onBackPressed();
            }
        }, "", AppManage.app_mainClickCntSwAd);


    }
}