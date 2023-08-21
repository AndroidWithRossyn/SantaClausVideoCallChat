package com.ide.santavideocall.kidsfunjoy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.ide.santavideocall.kidsfunjoy.R;

public class ExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}