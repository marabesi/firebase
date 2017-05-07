package com.auth.firebase.firebaseauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void onLogout(View view)
    {
        FirebaseAuth.getInstance().signOut();

        Intent main = new Intent(getBaseContext(), MainActivity.class);
        startActivity(main);
        finish();
    }
}
