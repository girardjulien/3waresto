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

public class InscriptionActivity extends AppCompatActivity implements WsManager.Listener {

    private TextView newLogin;
    private TextView newPassword;
    private TextView nom;
    private TextView prenom;
    private Button creer;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        newLogin = findViewById(R.id.new_login);
        newPassword = findViewById(R.id.new_password);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        creer = findViewById(R.id.creer);

        creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
                params.put("login", newLogin.getText().toString());
                params.put("pass", newPassword.getText().toString());
                params.put("nom", nom.getText().toString());
                params.put("prenom", prenom.getText().toString());

                WsManager wsManager = new WsManager();
                wsManager.executePostRequest("ws/resto/addCompte", InscriptionActivity.this, new Gson().toJson(params));
            }
        });

    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("tp9", errorMessage);
    }

    @Override
    public void onSuccess(String content) {
        Log.i("Succes : ", content);

        User user = gson.fromJson(content, User.class);

        Log.d("User : ", user.toString());

        if (user.getError() != null) {
            Toast toast = Toast.makeText(getApplicationContext(), "Erreur, vérifiez bien les champs que vous avez saisis", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            SharedPreferences sharedPref = getSharedPreferences("Profil", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("email", user.getEmail());
            editor.putString("nom", user.getNom());
            editor.putString("prenom", user.getPrenom());
            editor.apply();

            Intent mIntent = new Intent(InscriptionActivity.this, ListeRecettesActivity.class);
            startActivity(mIntent);
        }
    }
}
