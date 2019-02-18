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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.datamodels.Product;
import com.simonescanzani.scanzoseat.datamodels.Shop;
import com.simonescanzani.scanzoseat.services.RestController;
import com.simonescanzani.scanzoseat.ui.adapter.RecyclerAdapterShop;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.simonescanzani.scanzoseat.ui.adapter.RecyclerAdapterShop.*;

public class MainActivity extends AppCompatActivity implements  Response.Listener<String>, Response.ErrorListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    ActionBar actionBar;

    RecyclerView recyclerView;

    ArrayList<Shop> lstShop;
    List<Product> lstProduct;

    FloatingActionButton mFloatingActionButton;

    private RecyclerAdapterShop adapter;

    private RestController restController;

    private ProgressBar spinner;


    private final static String GRID_STATE = "GRID_STATE";
    private final static String PREF_NAME= "Preferences";

    private static final String ACCOUNT_NAME = "ACCOUNT_CREDENTIAL";
    private static final String JWT = "JWT";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        changeLayout(prefs.getBoolean(GRID_STATE, true));

        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);


        restController = new RestController(this);
        restController.getRequest(Shop.ENDPOINT, this, this);

        recyclerView = findViewById(R.id.recyclerview_shop);
        mFloatingActionButton = findViewById(R.id.fab);


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    public void setLayout(){
        if(getLayout()) {
            adapter = new RecyclerAdapterShop(R.layout.cardview_item,lstShop,this);
            GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
        }else{
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerAdapterShop(R.layout.view_item_shop,lstShop,this);
        }
        recyclerView.setAdapter(adapter);
    }

    //TODO hardcode
    public void setShop(){
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

    //TODO hardcode
    public void setProduct(){
        lstProduct = new ArrayList<Product>();
        lstProduct.add(new Product("Margherita","Acqua e Farina",5.0f, R.drawable.pizza_margherita_min));
        lstProduct.add(new Product("Boscaiola","Acqua e Farina",7.0f, R.drawable.pizza_boscaiola_min));
        lstProduct.add(new Product("Norcina","Acqua e Farina",6.0f, R.drawable.pizza_margherita_min));
        lstProduct.add(new Product("Diavola","Acqua e Farina",8.0f, R.drawable.pizza_margherita_min));
        lstProduct.add(new Product("Caprese","Acqua e Farina",5.5f, R.drawable.pizza_boscaiola_min));
        lstProduct.add(new Product("Alici e Tonno","Acqua e Farina",7.5f, R.drawable.pizza_margherita_min));
        lstProduct.add(new Product("Quattro Stagioni","Acqua e Farina",5.0f, R.drawable.pizza_boscaiola_min));
        lstProduct.add(new Product("Deppiù","Acqua e Farina",9.0f, R.drawable.pizza_margherita_min));
        lstProduct.add(new Product("Quattro Formaggi","Acqua e Farina",10.5f, R.drawable.pizza_margherita_min));
        lstProduct.add(new Product("Gorgonzola","Acqua e Farina",11.0f, R.drawable.pizza_boscaiola_min));
        lstProduct.add(new Product("Wurstel","Acqua e Farina",10.2f, R.drawable.pizza_margherita_min));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        if (!getLayout()) {
            menu.findItem(R.id.change_layout).setIcon(R.drawable.ic_grid_view);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {
            SharedPreferences prefsAccount = getSharedPreferences(ACCOUNT_NAME, MODE_PRIVATE);
            if(prefsAccount.getString(JWT, "")!=""){
                startActivity(new Intent(this, AccountActivity.class));
                return true;
            }else {
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            }
        } else if (item.getItemId() == (R.id.change_layout)) {
            changeLayout(!getLayout());
            SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
            editor.putBoolean(GRID_STATE, getLayout());
            editor.apply();
            if(getLayout())
                item.setIcon(R.drawable.ic_list_view);
            else
                item.setIcon(R.drawable.ic_grid_view);
            setLayout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG,error.getMessage());
        Toast.makeText(this, error.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG, response);

        //Start Parsing
        try {
            lstShop = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response);
            //JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
            for(int i=0; i<jsonArray.length(); i++){
                Shop shop = new Shop(jsonArray.getJSONObject(i));
                lstShop.add(shop);
            }
            setLayout();
            spinner.setVisibility(View.INVISIBLE);
           // adapter.setData(lstShop);
        }catch (JSONException ex){
            ex.printStackTrace();
        }
    }
}
