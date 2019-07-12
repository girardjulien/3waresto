package com.example.a3wresto;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*if (connexion) {
                    Intent mIntent = new Intent(MainActivity.this, ListeRecettesActivity.class);
                    startActivity(mIntent);
                } else {*/
                    Intent mIntent = new Intent(MainActivity.this, ConnexionActivity.class);
                    startActivity(mIntent);
                //}
            }
        }, 3000);
    }
}
