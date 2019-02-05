package com.simonescanzani.scanzoseat.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.datamodels.Product;
import com.simonescanzani.scanzoseat.ui.adapter.RecyclerAdapterProduct;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    private View ViewLayout;
    private RecyclerAdapterProduct listAdapter;
    RecyclerView recyclerView;

    ArrayList<Product> lstProduct;

    private Menu menu;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewLayout = getLayoutInflater().inflate(R.layout.activity_shop, null);

        setProduct();

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(ViewLayout);

        recyclerView = findViewById(R.id.recyclerview_shop);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new RecyclerAdapterProduct(lstProduct, this);
        recyclerView.setAdapter(listAdapter);



        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        final String Title = intent.getExtras().getString("Title");
        final String Street = intent.getExtras().getString("Street");
        String MinPrice = intent.getExtras().getString("MinPrice");
        int image = intent.getExtras().getInt("Thumbnail") ;

        setTitle(Title);

        img = findViewById(R.id.expandedImage);
        img.setImageResource(image);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Ristorante "+Title+" "+Street);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
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
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });
    }

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
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_shop, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_info) {
            return true;
        }

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
