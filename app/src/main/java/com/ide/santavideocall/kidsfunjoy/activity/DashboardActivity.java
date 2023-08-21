package com.ide.santavideocall.kidsfunjoy.activity;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ide.santavideocall.kidsfunjoy.R;
import com.pesonal.adsdk.AppManage;

import pl.droidsonroids.gif.GifImageView;

public class DashboardActivity extends AppCompatActivity {
    ImageView video_call, voice_call, chat, advice,mrs_claus;
    GifImageView gift_box;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        video_call = findViewById(R.id.video_call);
        voice_call = findViewById(R.id.voice_call);
        chat = findViewById(R.id.chat);
        advice = findViewById(R.id.advice);
        mrs_claus = findViewById(R.id.mrs_claus);
        mrs_claus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c();
            }
        });

        AppManage.getInstance(DashboardActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(DashboardActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], "");
        gift_box = findViewById(R.id.gift_box);
        gift_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, ChristmasActivity.class);
                        startActivity(intent);
                    }
                }, AppManage.ADMOB, AppManage.app_mainClickCntSwAd);

            }
        });
        video_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, VideoMeetingActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
        voice_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, VoiceMeetingActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, ChatActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);


            }
        });
        advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(DashboardActivity.this, AdviceActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
        checkPermission();
        requestPermission();
    }
    @SuppressLint("ResourceType")
    public void c() {
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_audio);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawableResource(17170445);
        CardView crd1 = (CardView) dialog.findViewById(R.id.crd1);
        CardView crd2 = (CardView) dialog.findViewById(R.id.crd2);
        CardView crd3 = (CardView) dialog.findViewById(R.id.crd3);

        crd1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(DashboardActivity.this,MrsSantaRejectVideoCallActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        crd2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(DashboardActivity.this,RejectMrsSantaAudioCallActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        crd3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppManage.getInstance(DashboardActivity.this).showInterstitialAd(DashboardActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(DashboardActivity.this,MrsSantaChatActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        dialog.show();
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.CAMERA);

        if (result == PackageManager.PERMISSION_GRANTED || result1 == PackageManager.PERMISSION_GRANTED || result2 == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DashboardActivity.this, ThopThankUActivity.class);
        startActivity(intent);
    }
}