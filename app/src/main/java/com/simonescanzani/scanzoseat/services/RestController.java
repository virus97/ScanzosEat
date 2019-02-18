package com.simonescanzani.scanzoseat.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class RestController {

    private final static String BASE_URL = "http://138.68.86.70";
    private final static String VERSION = "";

    private static final String TAG = RestController.class.getSimpleName();

    RequestQueue queue;

    public RestController(Context context){
        queue = Volley.newRequestQueue(context);
    }

    public void getRequest(String endpoint, Response.Listener<String> success, Response.ErrorListener error){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL.concat(VERSION).concat(endpoint),
                success, error);

        queue.add(stringRequest);

    }

    public void postRequest(String endpoint, Response.Listener<String> success, Response.ErrorListener error, final Map<String,String> params){
        String url = BASE_URL.concat(VERSION).concat(endpoint);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                success, error){
            @Override
            protected Map<String, String> getParams(){
                return params;
            }
        };


        stringRequest.setShouldCache(false);

        queue.add(stringRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                queue.getCache().clear();
            }
        });
    }
}
