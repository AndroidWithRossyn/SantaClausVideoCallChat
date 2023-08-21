package com.ide.santavideocall.kidsfunjoy.activity;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ide.santavideocall.kidsfunjoy.R;
import com.pesonal.adsdk.AppManage;

public class PrivacyPolicyActivity extends AppCompatActivity {
    RelativeLayout agree_btn;
    CheckBox checkbox;
    TextView goto_privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        goto_privacy = findViewById(R.id.goto_privacy);
        checkbox = findViewById(R.id.checkBox);
        AppManage.getInstance(PrivacyPolicyActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(PrivacyPolicyActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        agree_btn = findViewById(R.id.agree_btn);
        agree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()) {
                    AppManage.getInstance(PrivacyPolicyActivity.this).showInterstitialAd(PrivacyPolicyActivity.this, new AppManage.MyCallback() {
                        public void callbackCall() {
                            Intent intent = new Intent(PrivacyPolicyActivity.this, TimerActivity.class);
                            startActivity(intent);
                        }
                    }, "", AppManage.app_mainClickCntSwAd);
                } else {
                    Toast.makeText(PrivacyPolicyActivity.this, "Please Accept Privacy Policy", Toast.LENGTH_SHORT).show();
                }

            }
        });
        goto_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://techiemediaadvertising.blogspot.com/2022/06/techiemedia-inc.html");
            }
        });


    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onBackPressed() {

        PrivacyPolicyActivity.super.onBackPressed();
        overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);

    }

}