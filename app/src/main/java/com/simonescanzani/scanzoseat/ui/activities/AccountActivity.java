package com.simonescanzani.scanzoseat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.SharedPreferencesUtils;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    private Menu menu;
    private ImageView img;
    private Button btnEsci;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_account);

        img = findViewById(R.id.expandedImage);
        btnEsci = findViewById(R.id.btnEsci);
        btnEsci.setOnClickListener(this);

        img.setImageResource(R.drawable.photo);

        String username = SharedPreferencesUtils.getStringValue(this, SharedPreferencesUtils.USERNAME);

        final Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(username);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        AppBarLayout mAppBarLayout = findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.login_menu);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.login_menu);
                }
            }
        });
    }


    @Override
    public void onClick(View v){
        if(v.getId()==R.id.btnEsci){

            SharedPreferencesUtils.putValue(AccountActivity.this, SharedPreferencesUtils.USERNAME,null);
            SharedPreferencesUtils.putValue(AccountActivity.this, SharedPreferencesUtils.EMAIL,null);
            SharedPreferencesUtils.putValue(AccountActivity.this, SharedPreferencesUtils.JWT,null);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_account, menu);
        hideOption(R.id.login_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
