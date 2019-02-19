package com.simonescanzani.scanzoseat.ui.activities;

import android.content.Intent;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.SharedPreferencesUtils;
import com.simonescanzani.scanzoseat.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.simonescanzani.scanzoseat.Utilities.isUserNameValid;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener {

    final int LEN_PASS=6;
    Button btnRegister;
    EditText edtxEmail, edtxPass, edtxUsername;

    private RestController restController;

    private ProgressBar spinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnRegister = findViewById(R.id.btnRegisterNow);
        edtxEmail = findViewById(R.id.edtxEmail);
        edtxPass = findViewById(R.id.edtxPass);
        edtxUsername = findViewById(R.id.edtxUsername);

        spinner= findViewById(R.id.progressBarLoading);

        edtxEmail.addTextChangedListener(loginTextWatcher);
        edtxPass.addTextChangedListener(loginTextWatcher);
        edtxUsername.addTextChangedListener(loginTextWatcher);


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
            String usernameInput = edtxUsername.getText().toString().trim();

            btnRegister.setEnabled(isEmailValid(emailInput) && !(passwordInput.length()<LEN_PASS) && isUserNameValid(usernameInput));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
            spinner.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            if(jsonUser.getString("confirmed").equals("true")){
                Toast.makeText(RegisterActivity.this, "Confermato!", Toast.LENGTH_SHORT).show();

                SharedPreferencesUtils.putValue(RegisterActivity.this, SharedPreferencesUtils.JWT,TOKEN);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
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
            params.put("username", edtxUsername.getText().toString());
            params.put("password",edtxPass.getText().toString());
            restController = new RestController(this);
            restController.postRequest("/auth/local/register", this, this, params);
            btnRegister.setEnabled(false);
        }
    }
}
