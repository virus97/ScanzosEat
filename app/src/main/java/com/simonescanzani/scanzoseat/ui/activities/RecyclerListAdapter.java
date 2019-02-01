package com.simonescanzani.scanzoseat.ui.activities;

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

import java.util.ArrayList;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ContactHolder> {

    private ArrayList<Shop> shoplist;
    private Context mContext;


    public RecyclerListAdapter(ArrayList<Shop> contactsList, Context context) {
        this.shoplist = contactsList;
        this.mContext = context;
    }



    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.listview_item_shop, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public int getItemCount() {
        return shoplist == null? 0: shoplist.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, final int position) {
        final Shop shop = shoplist.get(position);

        holder.setContactName(shop.getTitle());
        holder.setContactNumber(shop.getCategory());
        holder.setThumbnail(shop.getThumbnail());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,ShopActivity.class);

                // passing data to the shop activity
                intent.putExtra("Title",shoplist.get(position).getTitle());
                intent.putExtra("Description",shoplist.get(position).getDescription());
                intent.putExtra("Thumbnail",shoplist.get(position).getThumbnail());
                // start the activity
                mContext.startActivity(intent);

            }
        });


    }


    public class ContactHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtNumber;
        private ImageView imgShop;
        private CardView cardView ;

        public ContactHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.shop_title_id);
            txtNumber = itemView.findViewById(R.id.shop_category_id);
            imgShop = itemView.findViewById(R.id.shop_img_id);
            cardView = itemView.findViewById(R.id.cardview_id);

        }

        public void setContactName(String name) {
            txtName.setText(name);
        }

        public void setContactNumber(String number) {
            txtNumber.setText(number);
        }

        public void setThumbnail(int thumbnail){
            imgShop.setImageResource(thumbnail);
        }

    }
}