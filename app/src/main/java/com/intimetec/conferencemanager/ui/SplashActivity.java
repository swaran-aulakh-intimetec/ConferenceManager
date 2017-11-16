package com.intimetec.conferencemanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.intimetec.conferencemanager.constant.PreferenceConstant;
import com.intimetec.conferencemanager.preference.PreferenceManager;
import com.intimetec.conferencemanager.ui.booking.HomeActivity;
import com.intimetec.conferencemanager.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sendUserToCorrectStartScreen();
    }

    private void sendUserToCorrectStartScreen() {
        boolean isLogin = PreferenceManager.getFlag(this, PreferenceConstant.HAS_LOGIN);

        Intent intent = new Intent(this, isLogin ? HomeActivity.class : LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
