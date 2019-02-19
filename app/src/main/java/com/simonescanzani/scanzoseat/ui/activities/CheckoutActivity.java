package com.simonescanzani.scanzoseat.ui.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.datamodels.Order;
import com.simonescanzani.scanzoseat.datamodels.Product;
import com.simonescanzani.scanzoseat.datamodels.Shop;
import com.simonescanzani.scanzoseat.ui.adapter.RecyclerAdapterCheckout;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity implements RecyclerAdapterCheckout.OnItemRemovedListener {

    RecyclerView recyclerView;
    private RecyclerAdapterCheckout adapter;

    private ArrayList<Product> lstOrdini;

    TextView txtTotal;

    float price = 0;

    private Order order;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_checkout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtTotal = findViewById(R.id.txtTotal);

        recyclerView = findViewById(R.id.recyclerview_checkout);

        getData();
        price = getPrice();

        txtTotal.setText("TOTAL "+price+"€");

        order.setList(lstOrdini);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapterCheckout(R.layout.view_item_checkout, order,this);
        adapter.setOnItemRemovedListener(this);
        recyclerView.setAdapter(adapter);

    }

    //TODO hardcode
    public void getData(){
        order = new Order(new Shop("Lu Gulosone","Via Piazza Tevere 98, Rieti",8.0f,R.drawable.pizza_margherita_min));
        lstOrdini = new ArrayList<>();
        lstOrdini.add(new Product("Margherita","Acqua e Farina",5.0f, R.drawable.pizza_margherita_min));
        lstOrdini.add(new Product("Boscaiola","Acqua e Farina",7.0f, R.drawable.pizza_boscaiola_min));
        lstOrdini.add(new Product("Norcina","Acqua e Farina",6.0f, R.drawable.pizza_margherita_min));
        lstOrdini.add(new Product("Diavola","Acqua e Farina",8.0f, R.drawable.pizza_margherita_min));
        lstOrdini.add(new Product("Caprese","Acqua e Farina",5.5f, R.drawable.pizza_boscaiola_min));
        int cont = 1;
        for(Product i : lstOrdini){
            i.setQuantity(cont);
            cont++;
        }
    }


    public float getPrice(){
        float price = 0.0f;
        for(Product i : lstOrdini){
            price+=(i.getPrezzoNumber()*i.getQuantity());
        }
        order.setPrezzo(price);
        return price;
    }

    public boolean onOptionsItemSelected(MenuItem menu){
        if(menu.getItemId()==android.R.id.home){
            finish();
            return true;
        }else{
            return super.onOptionsItemSelected(menu);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onItemRemoved(float price, int quantity) {
        //TODO manageItemRemoved
        this.price-=(price*quantity);
        txtTotal.setText("TOTAL "+this.price+"€");
    }
}
