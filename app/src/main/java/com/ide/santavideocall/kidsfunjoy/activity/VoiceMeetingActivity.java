package com.ide.santavideocall.kidsfunjoy.activity;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ide.santavideocall.kidsfunjoy.R;
import com.ide.santavideocall.kidsfunjoy.reject.RejectAudioCallActivity;
import com.ide.santavideocall.kidsfunjoy.reject.RejectAudioCallFourActivity;
import com.ide.santavideocall.kidsfunjoy.reject.RejectAudioCallOneActivity;
import com.ide.santavideocall.kidsfunjoy.reject.RejectAudioCallTwoActivity;
import com.pesonal.adsdk.AppManage;

public class VoiceMeetingActivity extends AppCompatActivity {
    private ImageView pixie, glitzy, cosmo, jolly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_meeting);
        AppManage.getInstance(VoiceMeetingActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(VoiceMeetingActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        pixie = findViewById(R.id.pixie);
        glitzy = findViewById(R.id.glitzy);
        cosmo = findViewById(R.id.cosmo);
        jolly = findViewById(R.id.jolly);
        pixie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(VoiceMeetingActivity.this).showInterstitialAd(VoiceMeetingActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(VoiceMeetingActivity.this, RejectAudioCallActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        glitzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(VoiceMeetingActivity.this).showInterstitialAd(VoiceMeetingActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(VoiceMeetingActivity.this, RejectAudioCallOneActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        cosmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(VoiceMeetingActivity.this).showInterstitialAd(VoiceMeetingActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(VoiceMeetingActivity.this, RejectAudioCallTwoActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        jolly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(VoiceMeetingActivity.this).showInterstitialAd(VoiceMeetingActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(VoiceMeetingActivity.this, RejectAudioCallFourActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
    }
    @Override
    public void onBackPressed() {
        AppManage.getInstance(VoiceMeetingActivity.this).showInterstitialAd(VoiceMeetingActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                VoiceMeetingActivity.super.onBackPressed();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }
}