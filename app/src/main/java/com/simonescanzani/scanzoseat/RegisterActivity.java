package com.simonescanzani.scanzoseat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class RegisterActivity extends AppCompatActivity {

    final int LEN_PASS=6;
    final int LEN_PHONE=12;
    Button btnRegister;
    EditText edtxEmail, edtxPass, edtxNumberPhone;

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

        edtxEmail.addTextChangedListener(loginTextWatcher);
        edtxPass.addTextChangedListener(loginTextWatcher);
        edtxNumberPhone.addTextChangedListener(loginTextWatcher);

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

            btnRegister.setEnabled(isEmailValid(emailInput) && !(passwordInput.length()<LEN_PASS) && isNumberValid(numberPhoneInput));
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
            Log.i("menu","premo indietro");
            finish();
            return true;
        }else{
            return super.onOptionsItemSelected(menu);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
