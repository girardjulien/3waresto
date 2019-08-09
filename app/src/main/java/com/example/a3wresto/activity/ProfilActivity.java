package com.example.a3wresto.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a3wresto.R;

public class ProfilActivity extends AppCompatActivity {

    private TextView email;
    private TextView nom;
    private TextView prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        email = findViewById(R.id.email_desc);
        nom = findViewById(R.id.nom_desc);
        prenom = findViewById(R.id.prenom_desc);

        SharedPreferences sharedPref = getSharedPreferences("Profil", Context.MODE_PRIVATE);

        email.setText(sharedPref.getString("email", ""));
        nom.setText(sharedPref.getString("nom", ""));
        prenom.setText(sharedPref.getString("prenom", ""));
    }
}
