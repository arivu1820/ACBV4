package com.futureinspirator.acbaradise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {

    // An object of RecyclerView.RecycledViewPool
    // is created to share the Views
    // between the child and
    // the parent RecyclerViews
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ParentItem> itemList;

    ParentItemAdapter(List<ParentItem> itemList)
    {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        // Here we inflate the corresponding
        // layout of the parent item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parent_item, viewGroup, false);

        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ParentViewHolder holder,
            int position)
    {


        ParentItem parentItem = itemList.get(position);

//        holder.title.setText(parentamcs.get(position).getTitle());

        holder.totalitem.setText(itemList.get(position).getTotalitem());
        holder.yousaved.setText(itemList.get(position).getYousaved());
        holder.charge.setText(itemList.get(position).getCharge());
        holder.grandtotal.setText(itemList.get(position).getGrandtotal());
        holder.discount.setText(itemList.get(position).getDiscount());
        holder.payment.setText(itemList.get(position).getPayment());
        holder.key.setText(itemList.get(position).getOrderid());
        holder.date.setText(itemList.get(position).getDate());
        holder.number.setText(itemList.get(position).getNumber());
        holder.address.setText(itemList.get(position).getAddress());
        if(holder.payment.getText().toString().equals("0"))
        {
            holder.tick.setVisibility(View.VISIBLE);
            holder.pending.setVisibility(View.INVISIBLE);
            holder.payment.setText("   Paid");

        }
        else
        {
            holder.tick.setVisibility(View.INVISIBLE);
            holder.pending.setVisibility(View.VISIBLE);
            holder.payment.setText("   Pending");

        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.framelayout1.getVisibility() == view.VISIBLE)
                {
                    holder.framelayout1.setVisibility(View.GONE);
                    holder.showless.setVisibility(View.INVISIBLE);
                    holder.showmore.setVisibility(View.VISIBLE);
                    holder.show.setRotation(180);
                }
                else
                {
                    holder.framelayout1.setVisibility(View.VISIBLE);
                    holder.showless.setVisibility(View.VISIBLE);
                    holder.showmore.setVisibility(View.INVISIBLE);
                    holder.show.setRotation(0);
                }

            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.productlist.getContext(), LinearLayoutManager.VERTICAL, false);

        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItemList().size());

        ChildItemAdapter childItemAdapter = new ChildItemAdapter(parentItem.getChildItemList());
        holder.productlist.setLayoutManager(layoutManager);
        holder.productlist.setAdapter(childItemAdapter);
        holder.productlist.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount()
    {

        return itemList.size();
    }

    // This class is to initialize
    // the Views present in
    // the parent RecyclerView
    class ParentViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView productlist;
        private TextView totalitem,yousaved,charge,grandtotal,discount,key,payment,date,number,address,showmore,showless;
        private ImageView pending,tick,show;
        private CardView cardView;
        private FrameLayout framelayout1;

        ParentViewHolder(final View itemView)
        {
            super(itemView);
            productlist = itemView.findViewById(R.id.productlist);

            totalitem = itemView.findViewById(R.id.totalitem);
            yousaved = itemView.findViewById(R.id.yousaved);
            charge = itemView.findViewById(R.id.charge);
            grandtotal = itemView.findViewById(R.id.grandtotal);
            discount = itemView.findViewById(R.id.discount);
            key = itemView.findViewById(R.id.orderid);
            payment = itemView.findViewById(R.id.payment);
            date = itemView.findViewById(R.id.date);
            number = itemView.findViewById(R.id.number);
            address = itemView.findViewById(R.id.address);

            pending = itemView.findViewById(R.id.pending);
            tick = itemView.findViewById(R.id.tick);
            cardView = itemView.findViewById(R.id.cardview);
            framelayout1 = itemView.findViewById(R.id.framelayout1);
            showmore = itemView.findViewById(R.id.showmore);
            showless = itemView.findViewById(R.id.showless);
            show = itemView.findViewById(R.id.imageView4);


        }
    }
}

