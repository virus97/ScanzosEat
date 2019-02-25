package com.simonescanzani.scanzoseat.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.datamodels.Product;

import java.util.ArrayList;

public class RecyclerAdapterProduct extends RecyclerView.Adapter<RecyclerAdapterProduct.ListLayoutHolder> {

    private ArrayList<Product> productlist;
    private Context mContext;
    private float minPrice;


    public RecyclerAdapterProduct(ArrayList<Product> contactsList, float minPrice, Context context) {
        this.productlist = contactsList;
        this.mContext = context;
        this.minPrice=minPrice;
    }

    public RecyclerAdapterProduct.onQuantityChangedListener getOnQuantityChangedListener() {
        return onQuantityChangedListener;
    }

    public void setOnQuantityChangedListener(RecyclerAdapterProduct.onQuantityChangedListener onQuantityChangedListener) {
        this.onQuantityChangedListener = onQuantityChangedListener;
    }

    public interface onQuantityChangedListener{
        void onChange(float price);
    }

    private onQuantityChangedListener onQuantityChangedListener;



    @Override
    public ListLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.view_item_product, parent, false);
        return new ListLayoutHolder(view);
    }

    public void setData(ArrayList<Product> data){
        this.productlist = data;
        notifyDataSetChanged();
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
        holder.setPrice(product.getPrezzoString());
        holder.setImage(product.getImage_url());
        holder.setQuantity(product.getQuantity());
    }


    public class ListLayoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtTitle;
        private TextView txtIngredienti;
        private TextView txtPrice;
        private ImageView imgProduct;
        private CardView cardView ;
        private Button btnIncrementa, btnDecrementa;
        private EditText edtxQuantity;

        public ListLayoutHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.title_id);
            txtIngredienti = itemView.findViewById(R.id.description_id);
            txtPrice =itemView.findViewById(R.id.description1_id);
            imgProduct = itemView.findViewById(R.id.img_id);
            cardView = itemView.findViewById(R.id.cardview_id);
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

        public void setQuantity(int quantity){
            edtxQuantity.setText(String.valueOf(quantity));
        }

        public void setImage(String img){
            RequestManager requestManager = Glide.with(mContext);
            RequestBuilder requestBuilder = requestManager.load(img);
            requestBuilder.into(imgProduct);
        }

        @Override
        public void onClick(View v) {
            Product product = productlist.get(getAdapterPosition());
            if(v.getId()== R.id.btnAggiungi){
                product.increseQuantity();
                onQuantityChangedListener.onChange(product.getPrezzo());
                notifyItemChanged(getAdapterPosition());
            } else if (v.getId()==R.id.btnRimuovi){
                if(product.getQuantity()!=0) {
                    product.decreaseQuantity();
                    onQuantityChangedListener.onChange(product.getPrezzo() * -1);
                    notifyItemChanged(getAdapterPosition());
                }
            }
        }
    }
}