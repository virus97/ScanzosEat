package com.simonescanzani.scanzoseat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.Utilities;
import com.simonescanzani.scanzoseat.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener {

    LinearLayout currentLayout;

    Button btnLogin, btnRegister;
    Switch swtDarkMode;

    TextView lblDarkMode;

    EditText edtxMail, edtxPassword;

    final static int LEN_PASS = 6;

    private RestController restController;

    public ProgressBar spinner;


    public enum StateRequest {TRUE, FALSE, UNCHECKED}

    StateRequest stateRequest = StateRequest.UNCHECKED;

    public final Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate(R.layout.activity_login_light);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    protected void onCreate(@LayoutRes int layoutPassed) {
        setContentView(layoutPassed);

        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);
        edtxMail = findViewById(R.id.edtxMail);
        edtxPassword = findViewById(R.id.edtxPassword);

        restController = new RestController(this);

        spinner= findViewById(R.id.progressBarLoading);

        swtDarkMode = findViewById(R.id.swtDark);

        lblDarkMode = findViewById(R.id.lblDarkMode);

        currentLayout = findViewById(R.id.loginLinearLayout);

        if(hasInvitationCode())
            btnRegister.setVisibility(View.GONE);
        else
            btnRegister.setVisibility(View.VISIBLE);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);


        swtDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    onCreate(R.layout.activity_login_dark);
                else
                    onCreate(R.layout.activity_login_light);
            }
        });


        Log.i("Lifecycle","onCreate chiamato");
    }




    private boolean hasInvitationCode(){
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonLogin) {
            Log.i("Button", "Login premuto");
            if(Utilities.doLogin(this, edtxMail.getText().toString(),edtxPassword.getText().toString(), LEN_PASS)){
                btnLogin.setEnabled(false);
                spinner.setVisibility(View.VISIBLE);
                Map<String,String> params = new HashMap<>();
                params.put("identifier",edtxMail.getText().toString());
                params.put("password",edtxMail.getText().toString());
                restController.postRequest("/auth/local", this, this, params);
                if(stateRequest==StateRequest.TRUE || stateRequest==StateRequest.FALSE) {
                    spinner.setVisibility(View.GONE);
                    btnLogin.setEnabled(true);
                }
            }
        }else if(v.getId()==R.id.buttonRegister){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
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
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle", "onResume chiamato");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle","onRestart chiamato");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle","onPause chiamato");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle", "onStop chiamato");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle","onDestroy chiamato");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(LoginActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        try {
            //JSONArray jsonArray = new JSONArray(response);
            JSONObject json = new JSONObject(response).getJSONObject("user");
            Log.i("rispostaTotale",response);
            Log.i("jsonRisposta", json.toString());
            if(json.getString("confirmed").equals("true")){
                Toast.makeText(LoginActivity.this, "Confermato!", Toast.LENGTH_SHORT).show();
                stateRequest = StateRequest.TRUE;
                spinner.setVisibility(View.GONE);
            }else{
                stateRequest = StateRequest.FALSE;
                Toast.makeText(LoginActivity.this, "Problema!", Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException ex){
            Log.i("Eccezione", ex.getMessage());
        }
    }
}
