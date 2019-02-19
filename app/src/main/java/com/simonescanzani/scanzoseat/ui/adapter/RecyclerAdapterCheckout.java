package com.simonescanzani.scanzoseat.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.simonescanzani.scanzoseat.R;
import com.simonescanzani.scanzoseat.datamodels.Order;
import com.simonescanzani.scanzoseat.datamodels.Product;
import com.simonescanzani.scanzoseat.datamodels.Shop;

import java.util.ArrayList;

public class RecyclerAdapterCheckout extends RecyclerView.Adapter {

    private final Shop shop;
    private ArrayList<Product> orderList;
    private Context mContext;
    private int idVista;
    private final int CONT_PRE_ORDER = 1;

    private static final int PRODUCT_VIEW_TYPE = 0;
    private static final int TITLE_VIEW_TYPE = 1;

    public interface OnItemRemovedListener {
        void onItemRemoved(float price, int quantity);
    }


    private OnItemRemovedListener onItemRemovedListener;

    public RecyclerAdapterCheckout.OnItemRemovedListener getOnItemRemovedListener() {
        return onItemRemovedListener;
    }

    public void setOnItemRemovedListener(RecyclerAdapterCheckout.OnItemRemovedListener onItemRemovedListener) {
        this.onItemRemovedListener = onItemRemovedListener;
    }


    public RecyclerAdapterCheckout(int idVista, Order order, Context context) {
        this.orderList = order.getList();
        this.shop = order.getShop();
        this.mContext = context;
        this.idVista = idVista;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TITLE_VIEW_TYPE;

        return PRODUCT_VIEW_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == TITLE_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.view_item_checkout_title, parent,false);
            return new TitleViewHolder(view);
        }

        view = layoutInflater.inflate(idVista, parent, false);
        return new ListLayoutHolder(view);


    }

    @Override
    public int getItemCount() {
        return orderList == null ? 0 : orderList.size()+CONT_PRE_ORDER;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(position!=0) {
            final Product order = orderList.get(position-CONT_PRE_ORDER);
            ListLayoutHolder holderCasted = ((ListLayoutHolder)holder);
            holderCasted.setTitle(order.getNome());

            holderCasted.setDescription(order.getIngredienti());
            holderCasted.setPrice(order.getPrezzo());
            holderCasted.setThumbnail(order.getThumbnail());
            holderCasted.setQuantity(order.getQuantity());
        }else{
            TitleViewHolder holderCasted = ((TitleViewHolder)holder);
            holderCasted.setTitle(shop.getTitle());
            holderCasted.setStreet(shop.getStreet());
        }
    }


    public class ListLayoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtTitle;
        private TextView txtDescription;
        private TextView txtPrice;
        private ImageView imgOrder;
        private EditText edtxQuantity;
        private ImageButton btnRemove;
        private CardView cardView;

        public ListLayoutHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.title_id);
            txtDescription = itemView.findViewById(R.id.description_id);
            txtPrice = itemView.findViewById(R.id.description1_id);
            imgOrder = itemView.findViewById(R.id.img_id);
            cardView = itemView.findViewById(R.id.cardview_id);
            edtxQuantity = itemView.findViewById(R.id.edtxQuantity);
            btnRemove = itemView.findViewById(R.id.btnRemove);

            btnRemove.setOnClickListener(this);


        }

        public void setTitle(String name) {
            txtTitle.setText(name);
        }

        public void setDescription(String street) {
            txtDescription.setText(street);
        }

        public void setPrice(String minPrice) {
            txtPrice.setText(minPrice);
        }

        public void setThumbnail(int thumbnail) {
            imgOrder.setImageResource(thumbnail);
        }

        public void setQuantity(int quantity) {
            edtxQuantity.setText(String.valueOf(quantity));
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnRemove) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Vuoi veramente cancellare il prodotto?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onItemRemovedListener.onItemRemoved(orderList.get(getAdapterPosition()-CONT_PRE_ORDER).getPrezzoNumber(), orderList.get(getAdapterPosition()-CONT_PRE_ORDER).getQuantity());
                        orderList.remove(getAdapterPosition()-CONT_PRE_ORDER);
                        notifyItemRemoved(getAdapterPosition());
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }

        }
    }


    public class TitleViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle, txtStreet;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtShopTitle);
            txtStreet = itemView.findViewById(R.id.txtStreet);

        }

        public void setTitle(String title){
            txtTitle.setText(title);
        }

        public void setStreet(String street){
            txtStreet.setText(street);
        }

    }

}