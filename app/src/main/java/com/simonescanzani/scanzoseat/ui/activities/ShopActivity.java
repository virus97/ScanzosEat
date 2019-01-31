package com.simonescanzani.scanzoseat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.simonescanzani.scanzoseat.R;

public class ShopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
