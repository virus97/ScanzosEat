package com.simonescanzani.scanzoseat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.simonescanzani.scanzoseat.R;

public class ShopActivity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        tvtitle = findViewById(R.id.txttitle);
        tvdescription = findViewById(R.id.txtDesc);
        tvcategory = findViewById(R.id.txtCat);
        img = findViewById(R.id.shopthumbnail);


        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail") ;

        // Setting values

        tvtitle.setText(Title);
        tvdescription.setText(Description);
        img.setImageResource(image);

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
