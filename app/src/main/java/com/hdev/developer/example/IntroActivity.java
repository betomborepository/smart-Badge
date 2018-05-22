package com.hdev.developer.example;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.paolorotolo.appintro.AppIntro2;

import preferences.Spreference;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFadeAnimation();

        addSlide(new IntroFrag0());
        addSlide(new IntroFrag1());
        addSlide(new IntroFrag2());
        addSlide(new IntroFrag3());


    }

    private void finishing_intro() {
        if (Build.VERSION.SDK_INT < 23){
            new Spreference(this).sPsetter("isLaunched", true);
            startActivity(new Intent(IntroActivity.this, LoginActivity.class));

        }else {

            /**
             * If you need some sensitive permissions
             * just comment out and edit it according to your needs
             * */

           /* if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(SEND_SMS) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                new Spreferences(this).sPsetter("isLaunched", true);*/
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));/*
            }else requestPermissions(new String[]{READ_CONTACTS, SEND_SMS, READ_PHONE_STATE, CALL_PHONE}, 100);*/
        }

    }

    private void show_warning_before_exit() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(getResources().getString(R.string.important))
                .setMessage(getResources().getString(R.string.permission_refused_info))
                .setPositiveButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finishAffinity();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onSkipPressed(android.support.v4.app.Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finishing_intro();

    }

    @Override
    public void onDonePressed(android.support.v4.app.Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finishing_intro();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0){
            boolean isOK = true;
            for (int i = 0; i < grantResults.length; i++){
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                    isOK = false;
            }

            if (isOK) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                new Spreference(this).sPsetter("isLaunched", true);
            }else {
                show_warning_before_exit();
            }
        }

    }

    public static class IntroFrag0 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.splash_0, container, false);

        }
    }

    public static class IntroFrag1 extends Fragment{
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.splash_1, container, false);

        }
    }

    public static class IntroFrag2 extends Fragment{
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.splash_2, container, false);

        }
    }

    public static class IntroFrag3 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.splash_3, container, false);

        }
    }

}
