package com.smart.badge;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smart.badge.R;

import adapters.entity.User;
import service.DataCore;

public class LoginActivity extends AppCompatActivity {


    AppCompatButton login_btn;
    AppCompatEditText uname, passwd;
    private DatabaseReference mDatabase;

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
    private void authenticating(final String username, final String password) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);


        final boolean   canLog = DataCore.CanLog(username, password);
        progressDialog.show();
        //waiting for 3sec before running this process
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    progressDialog.dismiss();
                }catch (Exception e)
                {

                }



            }
        }, 1000);


        //tester dans la base
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            mDatabase = db.getReference("users");
            ValueEventListener valueEventListener = mDatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        User user = postSnapshot.getValue(User.class);
                        if(user.password.contains(password) && user.nom.contains(username))
                        {
                            GoTomainPage();
                            return;
                        }
                    }
                    Error();
                }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.wtf("une erreur dans firebase a été signalé", "une erreu dans firebase a été signalé");
            }
        });
    }

    private void  GoTomainPage()
    {

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }


    private void Error()
    {
        Toast.makeText(getApplicationContext(), "Mot de passe ou compte erroné!",
                Toast.LENGTH_SHORT).show();
    }


    public  void goToLoginIdenitifiant(View v)
    {
        startActivity(new Intent(LoginActivity.this, NFCLoginIdentifiant.class));
    }
}
