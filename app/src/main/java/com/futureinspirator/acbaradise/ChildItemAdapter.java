package com.futureinspirator.acbaradise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> ChildItemList;

    // Constructor
    ChildItemAdapter(List<ChildItem> childItemList)
    {
        this.ChildItemList = childItemList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_item, viewGroup, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position)
    {
        ChildItem childItem = ChildItemList.get(position);

        holder.title.setText(childItem.getTitle());
        holder.quantity.setText(childItem.getQuantity());
        holder.price.setText(childItem.getPrice());
        holder.totalprice.setText(childItem.getTotalprice());

    }

    @Override
    public int getItemCount()
    {
        return ChildItemList.size();
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView quantity;
        private TextView price;
        private TextView totalprice;

        ChildViewHolder(View itemView)
        {
            super(itemView);

            title = itemView.findViewById(R.id.productname);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
            totalprice = itemView.findViewById(R.id.totalprice);

        }
    }
}

