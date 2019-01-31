package com.simonescanzani.scanzoseat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.simonescanzani.scanzoseat.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Shop> lstShop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();

        lstShop = new ArrayList<>();
        lstShop.add(new Shop("The Vegitarian","Categorie Shop","Description shop",R.drawable.thevigitarian));
        lstShop.add(new Shop("The Wild Robot","Categorie Shop","Description shop",R.drawable.thewildrobot));
        lstShop.add(new Shop("Maria Semples","Categorie Shop","Description shop",R.drawable.mariasemples));
        lstShop.add(new Shop("The Martian","Categorie Shop","Description shop",R.drawable.themartian));
        lstShop.add(new Shop("He Died with...","Categorie Shop","Description shop",R.drawable.hediedwith));
        lstShop.add(new Shop("The Vegitarian","Categorie Shop","Description shop",R.drawable.thevigitarian));
        lstShop.add(new Shop("The Wild Robot","Categorie Shop","Description shop",R.drawable.thewildrobot));
        lstShop.add(new Shop("Maria Semples","Categorie Shop","Description shop",R.drawable.mariasemples));
        lstShop.add(new Shop("The Martian","Categorie Shop","Description shop",R.drawable.themartian));
        lstShop.add(new Shop("He Died with...","Categorie Shop","Description shop",R.drawable.hediedwith));
        lstShop.add(new Shop("The Vegitarian","Categorie Shop","Description shop",R.drawable.thevigitarian));
        lstShop.add(new Shop("The Wild Robot","Categorie Shop","Description shop",R.drawable.thewildrobot));
        lstShop.add(new Shop("Maria Semples","Categorie Shop","Description shop",R.drawable.mariasemples));
        lstShop.add(new Shop("The Martian","Categorie Shop","Description shop",R.drawable.themartian));
        lstShop.add(new Shop("He Died with...","Categorie Shop","Description shop",R.drawable.hediedwith));


        recyclerView = findViewById(R.id.recyclerview_shop);


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, lstShop);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.login_menu) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }else if(item.getItemId()==(R.id.checkout_menu)){
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
