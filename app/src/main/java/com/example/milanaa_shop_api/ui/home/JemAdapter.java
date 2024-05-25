package com.example.milanaa_shop_api.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.milanaa_shop_api.R;
import com.example.milanaa_shop_api.databinding.ItemProductBinding;
import com.example.milanaa_shop_api.models.ModelM;

import java.util.ArrayList;
import java.util.List;


public class JemAdapter extends RecyclerView.Adapter<JemAdapter.ViewHolder> {
    ItemProductBinding itemProductBinding;
    Context context;
    List<ModelM> list;
    ArrayList<ModelM> selected_list = new ArrayList<>();
    ArrayList<ModelM> selected_intoBacketList = new ArrayList<>();

    NavController navController;


    public JemAdapter(Context context, List<ModelM> list) {
        this.context = context;
        this.list = list;
    }


    public ArrayList<ModelM> getSelected_intoBacketList() {
        return selected_intoBacketList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemProductBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding itemProductBinding;

        public ViewHolder(@NonNull ItemProductBinding itemView) {
            super(itemView.getRoot());
            this.itemProductBinding = itemView;
        }

        public void onBind(ModelM modelM) {
            itemProductBinding.nameProductCard.setText(modelM.getModelTitle());
            itemProductBinding.priceCard.setText(String.valueOf(modelM.getModelPrice()));
            itemProductBinding.descriptionCard.setText(modelM.getModelDescription());

            Glide.with(context)
                    .load(list.get(getAdapterPosition()).getModelImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(itemProductBinding.imageCard);

            itemProductBinding.btnZoom.setOnClickListener(v->{
                selected_list.add(modelM);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("favorite", selected_list);
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_description, bundle);
                Log.e("TAG", "pass data to description!");
            });

            itemView.setOnClickListener(v1 -> {
                if (itemProductBinding.tovarFavoriteCheck.getVisibility()==View.INVISIBLE){
                    itemProductBinding.tovarFavoriteCheck.setVisibility(View.VISIBLE);
                    selected_intoBacketList.add(modelM);
                }else {
                    itemProductBinding.tovarFavoriteCheck.setVisibility(View.INVISIBLE);
                    selected_intoBacketList.remove(modelM);
                }
            } );
        }

    }
}