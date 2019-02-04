package com.simonescanzani.scanzoseat.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.datamodels.Shop;
import com.simonescanzani.scanzoseat.ui.activities.ShopActivity;

import java.util.List;


public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.GridLayoutHolder> {

    private Context mContext ;
    private List<Shop> mData ;


    public RecyclerGridAdapter(Context mContext, List<Shop> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public GridLayoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_shop,parent,false);

        return new GridLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(GridLayoutHolder holder, final int position) {
        holder.tv_shop_title.setText(mData.get(position).getTitle());
        holder.img_shop_thumbnail.setImageResource(mData.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GridLayoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_shop_title;
        ImageView img_shop_thumbnail;
        CardView cardView ;

        public GridLayoutHolder(View itemView) {
            super(itemView);

            tv_shop_title = itemView.findViewById(R.id.shop_title_id) ;
            img_shop_thumbnail = itemView.findViewById(R.id.shop_img_id);
            cardView = itemView.findViewById(R.id.cardview_id);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Shop item = mData.get(getAdapterPosition());

            Intent intent = new Intent(mContext, ShopActivity.class);

            // passing data to the shop activity
            intent.putExtra("Title",item.getTitle());
            intent.putExtra("MinPrice",item.getMinPrice());
            intent.putExtra("Thumbnail",item.getThumbnail());
            // start the activity
            mContext.startActivity(intent);
        }
    }
}
