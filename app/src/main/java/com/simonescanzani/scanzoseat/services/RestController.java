package com.simonescanzani.scanzoseat.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.simonescanzani.scanzoseat.SharedPreferencesUtils;
import com.simonescanzani.scanzoseat.ui.activities.CheckoutActivity;

import org.json.JSONObject;

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

    public void postRequest(String endpoint, Response.Listener<JSONObject> success, Response.ErrorListener error, final JSONObject jsonObject, final Map<String,String> headers){
        String url = BASE_URL.concat(VERSION).concat(endpoint);
        Log.i(TAG, url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, success, error){
            @Override
            public Map<String, String> getHeaders() {
                return headers;
            }
        };
        jsonObjectRequest.setShouldCache(false);
        queue.add(jsonObjectRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                queue.getCache().clear();
            }
        });
    }
}
