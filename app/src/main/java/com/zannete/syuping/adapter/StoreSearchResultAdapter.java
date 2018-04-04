package com.zannete.syuping.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zannete.syuping.R;
import com.zannete.syuping.model.Store;

import java.util.ArrayList;

public class StoreSearchResultAdapter extends RecyclerView.Adapter<StoreSearchResultAdapter.ViewHolder>{
  private ArrayList<Store> mDataset;

  class ViewHolder extends RecyclerView.ViewHolder{
    private TextView tvName;
    private TextView tvAddress;
    private TextView tvCity;
    private ConstraintLayout container;

    public ViewHolder(View itemView) {
      super(itemView);
      tvName = itemView.findViewById(R.id.tvName);
      tvAddress = itemView.findViewById(R.id.tvAddress);
      tvCity = itemView.findViewById(R.id.tvCity);
      container = itemView.findViewById(R.id.detailContainer);
    }

    public void setName(String name){tvName.setText(name);}
    public void setAddress(String address){tvAddress.setText(address);}
    public void setCity(String city){tvCity.setText(city);}
    public void setOnClickListener(View.OnClickListener listener){
      container.setOnClickListener(listener);
    }
  }

  public StoreSearchResultAdapter(ArrayList<Store> mDataset) {
    this.mDataset = mDataset;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_store_search_single, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Store store = mDataset.get(position);
    holder.setName(store.getName());
    holder.setAddress(store.getAddress());
    holder.setCity(store.getCity());
  }

  @Override
  public int getItemCount() {
    return mDataset.size();
  }

}
