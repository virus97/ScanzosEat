package com.simonescanzani.scanzoseat.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.datamodels.Product;

import java.util.ArrayList;

public class RecyclerAdapterProduct extends RecyclerView.Adapter<RecyclerAdapterProduct.ListLayoutHolder> {

    private ArrayList<Product> productlist;
    private Context mContext;


    public RecyclerAdapterProduct(ArrayList<Product> contactsList, Context context) {
        this.productlist = contactsList;
        this.mContext = context;
    }


    @Override
    public ListLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.view_item_product, parent, false);
        return new ListLayoutHolder(view);
    }

    @Override
    public int getItemCount() {
        return productlist == null? 0: productlist.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ListLayoutHolder holder, final int position) {
        final Product product = productlist.get(position);

        holder.setTitle(product.getNome());
        holder.setIngredienti(product.getIngredienti());
        holder.setPrice(product.getPrezzo());
        holder.setThumbnail(product.getThumbnail());
    }


    public class ListLayoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtTitle;
        private TextView txtIngredienti;
        private TextView txtPrice;
        private ImageView imgProduct;
        private CardView cardView ;
        private ProgressBar progressBar;
        private Button btnIncrementa, btnDecrementa;
        private EditText edtxQuantity;

        public ListLayoutHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.title_id);
            txtIngredienti = itemView.findViewById(R.id.description_id);
            txtPrice =itemView.findViewById(R.id.description1_id);
            imgProduct = itemView.findViewById(R.id.img_id);
            cardView = itemView.findViewById(R.id.cardview_id);
            progressBar = itemView.findViewById(R.id.progressBar);
            btnDecrementa = itemView.findViewById(R.id.btnRimuovi);
            btnIncrementa = itemView.findViewById(R.id.btnAggiungi);
            edtxQuantity = itemView.findViewById(R.id.edtxQuantity);

            cardView.setOnClickListener(this);
            btnIncrementa.setOnClickListener(this);
            btnDecrementa.setOnClickListener(this);

        }

        public void setTitle(String name) {
            txtTitle.setText(name);
        }

        public void setIngredienti(String ingredienti) {
            txtIngredienti.setText(ingredienti);
        }

        public void setPrice(String price){
            txtPrice.setText(price);
        }

        public void setThumbnail(int thumbnail){
            imgProduct.setImageResource(thumbnail);
        }

        @Override
        public void onClick(View v) {
            if(v.getId()== R.id.btnAggiungi){
                edtxQuantity.setText(String.valueOf(Integer.parseInt(edtxQuantity.getText().toString())+1));
                Log.i("button", "+");
            } else if (v.getId()==R.id.btnRimuovi){
                if(Integer.parseInt((edtxQuantity.getText().toString()))!=0){
                    edtxQuantity.setText(String.valueOf(Integer.parseInt(edtxQuantity.getText().toString())-1));
                }
                Log.i("button", "-");
            }
        }
    }
}