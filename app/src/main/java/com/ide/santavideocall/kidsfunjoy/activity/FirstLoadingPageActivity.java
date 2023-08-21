package com.ide.santavideocall.kidsfunjoy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.gson.Gson;
import com.ide.santavideocall.kidsfunjoy.R;
import com.ide.santavideocall.kidsfunjoy.retrofit.RetrofitInterface;
import com.pesonal.adsdk.AppManage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstLoadingPageActivity extends AppCompatActivity {
    private Context mContext;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private int success;
    private Timer tm;
    boolean isTimerStarted;
    FullScreenContentCallback fullScreenContentCallback;
    public static final String ACTION_CLOSE = "ACTION_CLOSE";
    //    private FirstLoadingPageActivity.FirstReceiver firstReceiver;
    public static RetrofitInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first_loading_page);
        mContext = this;
        activity = this;
        isTimerStarted = false;

        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                if (checkPermission()) {
                    starttimer();
                } else {
                    requestPermission();
                }
            }
        } catch (Exception e) {
        }

    }

    public void starttimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                isTimerStarted = true;
                Intent i = new Intent(FirstLoadingPageActivity.this, PrivacyPolicyActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();

            }
        };
        tm = new Timer();
        tm.schedule(task, 2500);
    }
    @Override
    public void onBackPressed() {
        AppManage.getInstance(FirstLoadingPageActivity.this).showInterstitialAd(FirstLoadingPageActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                Intent intent=new Intent(FirstLoadingPageActivity.this, ThopThankUActivity.class);
                startActivity(intent);
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }


    public void updatecounter() {
        Call<Object> call1 = apiInterface.updatecounter("idetech.santavideo.santavideogiftone");

        call1.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
//                Toast.makeText(NewYearGIF_Splace_Activity.this, response.message(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful() && response.body() != null) {

                    String data = new Gson().toJson(response.body());

                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        success = jsonObject.getInt("success");
                        Toast.makeText(getApplicationContext(), "updatecounter = " + success, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                call.cancel();
            }
        });
    }

    class FirstReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("FirstReceiver", "FirstReceiver");
            if (intent.getAction().equals(ACTION_CLOSE)) {
                FirstLoadingPageActivity.this.finish();
            }
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);

        if (result == PackageManager.PERMISSION_GRANTED || result1 == PackageManager.PERMISSION_GRANTED || result2 == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
//                    fatchHeliNative();
                    starttimer();
                } else {
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isTimerStarted = false;
    }
}