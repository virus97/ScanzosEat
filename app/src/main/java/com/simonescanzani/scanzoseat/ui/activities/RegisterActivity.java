package com.simonescanzani.scanzoseat.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.datamodels.Shop;
import com.simonescanzani.scanzoseat.services.RestController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.simonescanzani.scanzoseat.Utilities.isUserNameValid;
import static com.simonescanzani.scanzoseat.ui.adapter.RecyclerAdapterShop.getLayout;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener {

    final int LEN_PASS=6;
    final int LEN_PHONE=12;
    Button btnRegister;
    EditText edtxEmail, edtxPass, edtxNumberPhone;

    private RestController restController;

    private ProgressBar spinner;

    private final static String TOKEN_PREF = "Token";
    private final static String PREF_NAME= "Preferences";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnRegister = findViewById(R.id.btnRegisterNow);
        edtxEmail = findViewById(R.id.edtxEmail);
        edtxPass = findViewById(R.id.edtxPass);
        edtxNumberPhone = findViewById(R.id.edtxNumberPhone);

        spinner= findViewById(R.id.progressBarLoading);

        edtxEmail.addTextChangedListener(loginTextWatcher);
        edtxPass.addTextChangedListener(loginTextWatcher);
        edtxNumberPhone.addTextChangedListener(loginTextWatcher);


        btnRegister.setOnClickListener(this);

    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String emailInput = edtxEmail.getText().toString().trim();
            String passwordInput = edtxPass.getText().toString().trim();
            String numberPhoneInput = edtxNumberPhone.getText().toString().trim();

            btnRegister.setEnabled(isEmailValid(emailInput) && !(passwordInput.length()<LEN_PASS) && isUserNameValid(numberPhoneInput));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isNumberValid(CharSequence number) {
        return android.util.Patterns.PHONE.matcher(number).matches() && number.length()<=LEN_PHONE;
    }

    public boolean onOptionsItemSelected(MenuItem menu){
        if(menu.getItemId()==android.R.id.home){
            finish();
            return true;
        }else{
            return super.onOptionsItemSelected(menu);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("erroreRisposta", error.toString());
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonRisposta = new JSONObject(response);
            String TOKEN = jsonRisposta.get("jwt").toString();
            JSONObject jsonUser = jsonRisposta.getJSONObject("user");
            Log.i("taggami",response);
            Log.i("taggami", TOKEN);
            Log.i("taggami", jsonUser.toString());
            //spinner.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            if(jsonUser.getString("confirmed").equals("true")){
                Toast.makeText(RegisterActivity.this, "Confermato!", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
                editor.putString(TOKEN_PREF, TOKEN);
                editor.apply();
            }else{
                Toast.makeText(RegisterActivity.this, "Problema!", Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException ex){
            Log.i("Eccezione", ex.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnRegisterNow) {
            spinner.setVisibility(View.VISIBLE);
            Map<String,String> params = new HashMap<>();
            params.put("email",edtxEmail.getText().toString());
            params.put("username",edtxNumberPhone.getText().toString());
            params.put("password",edtxPass.getText().toString());
            restController = new RestController(this);
            restController.postRequest("/auth/local/register", this, this,params);
            btnRegister.setEnabled(false);
        }
            //far partire un caricamento e stopparlo in onResponse
        //disabilita l'onClick per non spammare le richieste
    }
}
