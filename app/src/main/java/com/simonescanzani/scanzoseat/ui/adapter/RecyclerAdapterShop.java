package com.simonescanzani.scanzoseat.ui.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.Utilities;
import com.simonescanzani.scanzoseat.datamodels.Shop;
import com.simonescanzani.scanzoseat.ui.activities.ShopActivity;


import java.util.ArrayList;

public class RecyclerAdapterShop extends RecyclerView.Adapter<RecyclerAdapterShop.ListLayoutHolder> {

    private ArrayList<Shop> shoplist;
    private Context mContext;
    private int idVista;
    private static boolean grid = true;


    public RecyclerAdapterShop(int idVista, ArrayList<Shop> contactsList, Context context) {
        this.shoplist = contactsList;
        this.mContext = context;
        this.idVista=idVista;
    }



    @Override
    public ListLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(idVista, parent, false);
        return new ListLayoutHolder(view);
    }

    @Override
    public int getItemCount() {
        return shoplist == null? 0: shoplist.size();
    }

    public void setData(ArrayList<Shop> data){
        this.shoplist = data;
    }



    @Override
    public void onBindViewHolder(@NonNull ListLayoutHolder holder, final int position) {
        final Shop shop = shoplist.get(position);
        holder.setTitle(shop.getTitle());
        holder.setStreet(shop.getStreet());
        holder.setMinPrice(shop.getMinPrice());
        //holder.setThumbnail(shop.getThumbnail());
        holder.setImage(shop.getImage_url());
    }


    public class ListLayoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtTitle;
        private TextView txtStreet;
        private TextView txtMinPrice;
        private ImageView imgShop;
        private ImageView imgShopMin;
        private CardView cardView ;

        private int columns;

        public ListLayoutHolder(View itemView) {
            super(itemView);

            columns = Utilities.calculateNoOfColumnsWidth(mContext);

            txtTitle = itemView.findViewById(R.id.title_id);
            txtStreet = itemView.findViewById(R.id.description_id);
            txtMinPrice = itemView.findViewById(R.id.description1_id);
            if((grid)&&((mContext.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)||((mContext.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT)&&columns>=4)))
                imgShopMin = itemView.findViewById(R.id.imgShop);

            imgShop = itemView.findViewById(R.id.img_id);
            cardView = itemView.findViewById(R.id.cardview_id);

            cardView.setOnClickListener(this);

        }

        public void setTitle(String name) {
            txtTitle.setText(name);
        }

        public void setStreet(String street) {
            txtStreet.setText(street);
        }

        public void setMinPrice(String minPrice){
            txtMinPrice.setText(minPrice);
        }

        public void setThumbnail(int thumbnail){
            imgShop.setImageResource(thumbnail);
        }

        public void setImage(String img){
            RequestManager requestManager = Glide.with(mContext);
            RequestBuilder requestBuilder = requestManager.load(img);
            requestBuilder.into(imgShop);
            if((grid)&&((mContext.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)||((mContext.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT)&&columns>=4)))
                requestBuilder.into(imgShopMin);
        }


        @Override
        public void onClick(View v) {
            if(v.getId()== R.id.cardview_id){
                Intent intent = new Intent(mContext, ShopActivity.class);

                // passing data to the shop activity
                Shop item = shoplist.get(getAdapterPosition());

                intent.putExtra("Title",item.getTitle());
                intent.putExtra("Street",item.getStreet());
                intent.putExtra("MinPrice",item.getMinPriceNumber());
                intent.putExtra("Thumbnail",item.getImage_url());
                intent.putExtra("id", item.getId());
                // start the activity
                mContext.startActivity(intent);
            }
        }
    }

    public static void changeLayout(boolean layout){
        grid=layout;
    }

    public static boolean getLayout(){
        return grid;
    }
}