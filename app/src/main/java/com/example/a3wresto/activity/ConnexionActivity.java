package com.example.a3wresto.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3wresto.R;
import com.example.a3wresto.manager.WsManager;
import com.example.a3wresto.model.User;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ConnexionActivity extends AppCompatActivity implements WsManager.Listener {

    private TextView login;
    private TextView password;
    private Button connexion;
    private Button inscription;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        login = findViewById(R.id.email);
        password = findViewById(R.id.password);
        connexion = findViewById(R.id.connexion);
        inscription = findViewById(R.id.inscription);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
                params.put("login", login.getText().toString());
                params.put("pass", password.getText().toString());
                WsManager wsManager = new WsManager();
                wsManager.executePostRequest("ws/resto/connexion", ConnexionActivity.this, new Gson().toJson(params));
            }
        });

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
                startActivity(mIntent);
            }
        });


    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("tp9", errorMessage);
    }

    @Override
    public void onSuccess(String content) {
        Log.i("Success : ", content);

        User user = gson.fromJson(content, User.class);

        Log.d("User : ", user.toString());

        if (user.getError() != null) {
            Toast toast = Toast.makeText(getApplicationContext(), "Vos identifiants sont erronés", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            SharedPreferences sharedPref = getSharedPreferences("Profil", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("email", user.getEmail());
            editor.putString("nom", user.getNom());
            editor.putString("prenom", user.getPrenom());
            editor.apply();

            Intent mIntent = new Intent(ConnexionActivity.this, ListeRecettesActivity.class);
            startActivity(mIntent);
        }
    }
}
