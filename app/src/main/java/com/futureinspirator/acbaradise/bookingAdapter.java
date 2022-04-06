package com.futureinspirator.acbaradise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class bookingAdapter extends RecyclerView.Adapter<bookingAdapter.adapter>{

    private ArrayList<parentbooking> bookings = new ArrayList<>();

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());


    public bookingAdapter()
    {

    }

    @NonNull
    @Override
    public adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookinglist,parent,false);
        adapter holder = new adapter(view);

        return holder;
    }
    

    @Override
    public void onBindViewHolder(@NonNull adapter holder, int position) {
        holder.title.setText(bookings.get(position).getTitle());
        holder.quantity.setText(bookings.get(position).getQuantity());
        holder.totalprice.setText(bookings.get(position).getTotalprice());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public void setbookings(ArrayList<parentbooking> bookings) {
        this.bookings = bookings;
        notifyDataSetChanged();
        
    }

    public static class adapter extends RecyclerView.ViewHolder{



        private TextView title;
        private TextView quantity;
        private TextView price;
        private TextView totalprice;
        
        public adapter(@NonNull View itemView)
        {
            super(itemView);

            title = itemView.findViewById(R.id.productname);
            quantity = itemView.findViewById(R.id.price);
            totalprice = itemView.findViewById(R.id.totalprice);

        }
    }


}
