package com.simonescanzani.scanzoseat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.simonescanzani.scanzoseat.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;

    RecyclerView recyclerView;
    ArrayList<Shop> lstShop;

    FloatingActionButton mFloatingActionButton;

    private RecyclerListAdapter listAdapter;

    private static boolean grid = true;

    private View ViewLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewLayout = getLayoutInflater().inflate(R.layout.activity_main, null);


        setContentView(ViewLayout);

        actionBar = getSupportActionBar();

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


        changeLayout();


    }

    public void changeLayout(){
        if(grid) {
            setContentView(ViewLayout);
            recyclerView = findViewById(R.id.recyclerview_shop);
            mFloatingActionButton = findViewById(R.id.fab);


            RecyclerGridAdapter adapter = new RecyclerGridAdapter(this, lstShop);
            recyclerView.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
        }else{

            setContentView(ViewLayout);
            recyclerView = findViewById(R.id.recyclerview_shop);
            mFloatingActionButton = findViewById(R.id.fab);


            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            listAdapter = new RecyclerListAdapter(lstShop, this);
            recyclerView.setAdapter(listAdapter);

            listAdapter.notifyDataSetChanged();
        }

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CheckoutActivity.class));
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mFloatingActionButton.hide();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mFloatingActionButton.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (item.getItemId() == (R.id.change_layout)) {
            grid = !grid;
            if(grid)
                item.setIcon(R.drawable.ic_list_view);
            else
                item.setIcon(R.drawable.ic_grid_view);
            changeLayout();
        }
        return super.onOptionsItemSelected(item);
    }

}
