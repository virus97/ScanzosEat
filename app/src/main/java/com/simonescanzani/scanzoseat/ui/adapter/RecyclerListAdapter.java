package com.simonescanzani.scanzoseat.ui.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.datamodels.Shop;
import com.simonescanzani.scanzoseat.ui.activities.MainActivity;
import com.simonescanzani.scanzoseat.ui.activities.ShopActivity;

import java.util.ArrayList;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ListLayoutHolder> {

    private ArrayList<Shop> shoplist;
    private Context mContext;



    public RecyclerListAdapter(ArrayList<Shop> contactsList, Context context) {
        this.shoplist = contactsList;
        this.mContext = context;
    }



    @Override
    public ListLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.listview_item_shop, parent, false);
        return new ListLayoutHolder(view);
    }

    @Override
    public int getItemCount() {
        return shoplist == null? 0: shoplist.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ListLayoutHolder holder, final int position) {
        final Shop shop = shoplist.get(position);

        holder.setTitle(shop.getTitle());
        holder.setStreet(shop.getStreet());
        holder.setMinPrice(shop.getMinPrice());
        holder.setThumbnail(shop.getThumbnail());

    }


    public class ListLayoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtTitle;
        private TextView txtStreet;
        private TextView txtMinPrice;
        private ImageView imgShop;
        private CardView cardView ;

        public ListLayoutHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.shop_title_id);
            txtStreet = itemView.findViewById(R.id.shop_street_id);
            txtMinPrice =itemView.findViewById(R.id.shop_min_price_id);
            imgShop = itemView.findViewById(R.id.shop_img_id);
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

        @Override
        public void onClick(View v) {
            if(v.getId()== R.id.cardview_id){
                Intent intent = new Intent(mContext, ShopActivity.class);

                // passing data to the shop activity
                Shop item = shoplist.get(getAdapterPosition());

                intent.putExtra("Title",item.getTitle());
                intent.putExtra("Street",item.getStreet());
                intent.putExtra("MinPrice",item.getMinPrice());
                intent.putExtra("Thumbnail",item.getThumbnail());
                // start the activity
                mContext.startActivity(intent);
            }
        }
    }
}