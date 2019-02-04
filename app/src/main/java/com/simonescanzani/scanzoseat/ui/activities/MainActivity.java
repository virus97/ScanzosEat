package com.simonescanzani.scanzoseat.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.simonescanzani.scanzoseat.datamodels.Shop;
import com.simonescanzani.scanzoseat.ui.adapter.RecyclerGridAdapter;
import com.simonescanzani.scanzoseat.ui.adapter.RecyclerListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;

    RecyclerView recyclerView;
    ArrayList<Shop> lstShop;

    FloatingActionButton mFloatingActionButton;

    private RecyclerListAdapter listAdapter;

    private static boolean grid = true;

    private View ViewLayout;

    private final static String GRID_STATE = "GRID_STATE";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewLayout = getLayoutInflater().inflate(R.layout.activity_main, null);

        SharedPreferences prefs = getSharedPreferences(GRID_STATE, MODE_PRIVATE);

        grid = prefs.getBoolean(GRID_STATE, true);

        setContentView(ViewLayout);

        actionBar = getSupportActionBar();

        setData();

        setLayout();

    }

    public void setLayout(){
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

            //listAdapter.notifyDataSetChanged();
        }

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, CheckoutActivity.class));
                Intent intent = new Intent(MainActivity.this,CheckoutActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
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

    public void setData(){
        lstShop = new ArrayList<>();
        lstShop.add(new Shop("Lu Gulosone","Via Piazza Tevere 98, Rieti",8.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Pianeta Pizza","Viale de Juliis 11, Rieti",10.5f,R.drawable.pizza_boscaiola_min));
        lstShop.add(new Shop("Il Viale","Viale Maraini snc, Rieti",11.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Da Tito","Via Liberato di Medenetto 20, Rieti",14.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Le Lumiere","Via Cintia 33, Rieti",5.70f,R.drawable.pizza_boscaiola_min));
        lstShop.add(new Shop("Lu Gulosone","Via Piazza Tevere 98, Rieti",8.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Pianeta Pizza","Viale de Juliis 11, Rieti",10.5f,R.drawable.pizza_boscaiola_min));
        lstShop.add(new Shop("Il Viale","Viale Maraini snc, Rieti",11.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Da Tito","Via Liberato di Medenetto 20, Rieti",14.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Le Lumiere","Via Cintia 33, Rieti",5.70f,R.drawable.pizza_boscaiola_min));
        lstShop.add(new Shop("Lu Gulosone","Via Piazza Tevere 98, Rieti",8.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Pianeta Pizza","Viale de Juliis 11, Rieti",10.5f,R.drawable.pizza_boscaiola_min));
        lstShop.add(new Shop("Il Viale","Viale Maraini snc, Rieti",11.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Da Tito","Via Liberato di Medenetto 20, Rieti",14.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Le Lumiere","Via Cintia 33, Rieti",5.70f,R.drawable.pizza_boscaiola_min));
        lstShop.add(new Shop("Lu Gulosone","Via Piazza Tevere 98, Rieti",8.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Pianeta Pizza","Viale de Juliis 11, Rieti",10.5f,R.drawable.pizza_boscaiola_min));
        lstShop.add(new Shop("Il Viale","Viale Maraini snc, Rieti",11.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Da Tito","Via Liberato di Medenetto 20, Rieti",14.0f,R.drawable.pizza_margherita_min));
        lstShop.add(new Shop("Le Lumiere","Via Cintia 33, Rieti",5.70f,R.drawable.pizza_boscaiola_min));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        if (!grid) {
            menu.findItem(R.id.change_layout).setIcon(R.drawable.ic_grid_view);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (item.getItemId() == (R.id.change_layout)) {
            grid = !grid;
            SharedPreferences.Editor editor = getSharedPreferences(GRID_STATE, MODE_PRIVATE).edit();
            editor.putBoolean(GRID_STATE,grid);
            editor.apply();
            if(grid)
                item.setIcon(R.drawable.ic_list_view);
            else
                item.setIcon(R.drawable.ic_grid_view);
            setLayout();
        }
        return super.onOptionsItemSelected(item);
    }

}
