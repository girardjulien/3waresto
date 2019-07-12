package com.example.a3wresto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.Gson;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConnexionActivity extends AppCompatActivity {

    private TextView login;
    private TextView password;
    private Button connexion;
    private Button inscription;

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

                sendRequest(1, "ws/resto/connexion", params);
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

    public void sendRequest(final int idRequest, String target, Map<String, String> params){

        if(params == null){
            params = new HashMap<>();
        }

        final String jsonString = new Gson().toJson(params);


        Map<String, String> header = new HashMap<>();

        header.put("Content-Type","application/json");

        //get

        Fuel.post("http://51.15.254.4:9001/"+target).body(jsonString, Charset.forName("UTF-8")).header(header).responseString(new Handler<String>() {

            @Override
            public void failure(Request request, Response response, FuelError error) {
                Log.i("Error : ", "Erreur : "+error.toString());
                //do something when it is failure

                /*if(wsListener == null){
                    return;
                }

                wsListener.errorRequest(idRequest);*/

            }

            @Override
            public void success(Request request, Response response, String data) {
                Log.i("Succes : ", data);

                Intent mIntent = new Intent(ConnexionActivity.this, ListeRecettesActivity.class);
                startActivity(mIntent);

            }

        });
    }
}
