package com.smart.badge;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.smart.badge.R;

public class LoginActivity extends AppCompatActivity {


    AppCompatButton login_btn;
    AppCompatEditText uname, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

    }

    /**
     * Initializing views from the xml layout file
     * */
    private void initialize() {
        login_btn = findViewById(R.id.login_btn);
        uname = findViewById(R.id.user);
        passwd = findViewById(R.id.pass);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString();
                if (username.isEmpty() || username.equals("")){
                    Toast.makeText(LoginActivity.this,
                            getResources().getString(R.string.x_not_should_not_empty, getResources().getString(R.string.username)),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = passwd.getText().toString();
                if (password.isEmpty() || password.equals("")){
                    Toast.makeText(LoginActivity.this,
                            getResources().getString(R.string.x_not_should_not_empty, getResources().getString(R.string.pass)),
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                authenticating(username, password);

            }
        });

    }

    /**
     * Simulating the authentication for 3 sec, then redirecting to the MainActivity
     * */
    private void authenticating(String username, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        //waiting for 3sec before running this process
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }, 3000);
    }
}
