package com.example.a3wresto;

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
import java.util.HashMap;
import java.util.Map;

public class InscriptionActivity extends AppCompatActivity {

    private TextView newLogin;
    private TextView newPassword;
    private TextView nom;
    private TextView prenom;
    private Button creer;

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

                sendRequest(1, "ws/resto/addCompte", params);
            }
        });

    }

    public void sendRequest(final int idRequest, String target, Map<String, String> params){

        if(params == null){
            params = new HashMap<>();
        }

        String jsonString = new Gson().toJson(params);


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

                //do something when it is successful

                /*if(wsListener == null){
                    return;
                }

                wsListener.successRequest(idRequest, data);*/

            }

        });
    }
}
