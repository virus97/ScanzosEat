package com.simonescanzani.scanzoseat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout currentLayout;

    Button btnLogin, btnRegister;
    Switch swtDarkMode;

    TextView lblDarkMode;

    EditText edtxMail, edtxPassword;

    final static int LEN_PASS = 6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate(R.layout.activity_main_light);
    }

    protected void onCreate(@LayoutRes int layoutPassed) {
        setContentView(layoutPassed);

        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);
        edtxMail = findViewById(R.id.edtxMail);
        edtxPassword = findViewById(R.id.edtxPassword);

        swtDarkMode = findViewById(R.id.swtDark);

        lblDarkMode = findViewById(R.id.lblDarkMode);

        currentLayout = findViewById(R.id.mainLinearLayout);

        if(hasInvitationCode())
            btnRegister.setVisibility(View.GONE);
        else
            btnRegister.setVisibility(View.VISIBLE);

        btnLogin.setOnClickListener(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                Log.i("Button", "Register premuto");
            }
        });

        swtDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           /* @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    currentLayout.setBackgroundColor(parseColor("#FFFFFF"));
                    currentLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.backgroundDark));
                    edtxMail.setHintTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundLight));
                    edtxPassword.setHintTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundLight));
                    lblDarkMode.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundLight));
                    edtxMail.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundLight));
                    edtxPassword.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundLight));
                    lblDarkMode.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundLight));
                }
                else {
                    currentLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.backgroundLight));
                    edtxMail.setHintTextColor(ContextCompat.getColor(MainActivity.this,R.color.hint_colorLight));
                    edtxPassword.setHintTextColor(ContextCompat.getColor(MainActivity.this,R.color.hint_colorLight));
                    lblDarkMode.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundDark));
                    edtxMail.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundDark));
                    edtxPassword.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundDark));
                    lblDarkMode.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundDark));
                }

            }*/

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    onCreate(R.layout.activity_main_dark);
                else
                    onCreate(R.layout.activity_main_light);
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
               // Intent intent = new Intent(this, WelcomeActivity.class);
               // intent.putExtra("email",edtxMail.getText().toString());
               // startActivity(intent);
            }
        }
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
}
