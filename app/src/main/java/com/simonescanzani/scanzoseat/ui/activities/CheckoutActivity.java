package com.simonescanzani.scanzoseat.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.SharedPreferencesUtils;
import com.simonescanzani.scanzoseat.datamodels.Order;
import com.simonescanzani.scanzoseat.datamodels.Product;
import com.simonescanzani.scanzoseat.datamodels.Shop;
import com.simonescanzani.scanzoseat.services.AppDatabase;
import com.simonescanzani.scanzoseat.services.RestController;
import com.simonescanzani.scanzoseat.ui.adapter.RecyclerAdapterCheckout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener ,RecyclerAdapterCheckout.OnItemRemovedListener, Response.Listener<JSONObject>, Response.ErrorListener {

    RecyclerView recyclerView;
    private RecyclerAdapterCheckout adapter;

    private RestController restController;


    private ArrayList<Product> lstOrdini = new ArrayList<>();

    TextView txtTotal;

    float price = 0;

    private Order order;

    private Button btnPay;

    private static final String TAG = CheckoutActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_checkout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtTotal = findViewById(R.id.txtTotal);

        recyclerView = findViewById(R.id.recyclerview_checkout);
        btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapterCheckout(R.layout.view_item_checkout, null,this);
        adapter.setOnItemRemovedListener(this);
        recyclerView.setAdapter(adapter);

        new DatabaseAsyncTask().execute();

    }

    private class DatabaseAsyncTask extends AsyncTask<Void, Void, Void> {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Void doInBackground(Void... voids) {

            AppDatabase appDatabase = AppDatabase.createIstance(CheckoutActivity.this);
            List<Order> allOrder = appDatabase.orderDao().getAll();
            if (allOrder != null && allOrder.size() > 0) {
                order = allOrder.get(0);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i("shoppete", order.getShop().getTitle());
            txtTotal.setText("TOTAL "+ order.getPrezzo() +"€");
            adapter.setData(order);
        }
    }

    public float getPrice(){
        float price = 0.0f;
        for(Product i : lstOrdini){
            price+=(i.getPrezzo()*i.getQuantity());
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

    //TODO hardcode
    public JSONArray createData(){
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", "5c6570b71a39104f0ba41137");
            jsonObject.put("quantity", 2);
            jsonArray.put(jsonObject);
            jsonObject = new JSONObject();
            jsonObject.put("id", "5c6570d61a39104f0ba41138");
            jsonObject.put("quantity", 5);
            jsonArray.put(jsonObject);
        }catch (JSONException ex){
            Log.e(TAG,ex.getMessage());
        }

        return jsonArray;
    }

    @Override
    public void onItemRemoved(float price, int quantity) {
        //TODO manageItemRemoved
        this.price-=(price*quantity);
        txtTotal.setText("TOTAL "+this.price+"€");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnPay){
            restController = new RestController(this);
            Log.i(TAG,createData().toString());

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("restaurant", "5c6570061a39104f0ba41134");
                jsonObject.put("user", SharedPreferencesUtils.getStringValue(this, SharedPreferencesUtils.ID));
                jsonObject.put("amount", 10);
                jsonObject.put("products", createData());
            }catch(JSONException ex){
                Log.e(TAG, ex.getMessage());
            }

            Map<String,String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer ".concat(SharedPreferencesUtils.getStringValue(this, SharedPreferencesUtils.JWT)));

            restController.postRequest("/orders", this, this, jsonObject, headers);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.i(TAG, response.toString());
    }
}
