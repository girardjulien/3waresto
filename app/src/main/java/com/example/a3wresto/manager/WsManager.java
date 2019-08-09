package com.example.a3wresto.manager;

import android.util.Log;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class WsManager {

    public static final String URL = "http://51.15.254.4:9001/";

    public void executeGetRequest(String target, final Listener listener){

        Fuel.get(URL + target,null).responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError fuelError) {
                Log.e("tp9","error : " + fuelError);
                listener.onFailure(fuelError.toString());
            }

            @Override
            public void success(Request request, Response response, String s) {
                Log.d("tp9","success : " + s);
                listener.onSuccess(s);
            }
        });
    }

    public void executePostRequest(String target, final Listener listener){

        Fuel.post(URL + target,null).responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError fuelError) {
                Log.e("tp9","error : " + fuelError);
                listener.onFailure(fuelError.toString());
            }

            @Override
            public void success(Request request, Response response, String s) {
                Log.d("tp9","success : " + s);
                listener.onSuccess(s);
            }
        });
    }

    public void executePostRequest(String target, final Listener listener, String jsonString){

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json");

        Fuel.post(URL + target).body(jsonString, Charset.forName("UTF-8")).header(header).responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError fuelError) {
                Log.e("tp9","error : " + fuelError);
                listener.onFailure(fuelError.toString());
            }

            @Override
            public void success(Request request, Response response, String s) {
                Log.d("tp9","success : " + s);
                listener.onSuccess(s);
            }
        });
    }

    public interface Listener {

        void onFailure(String errorMessage);

        void onSuccess(String content);
    }
}
