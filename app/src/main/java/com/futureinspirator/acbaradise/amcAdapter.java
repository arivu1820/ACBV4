package com.futureinspirator.acbaradise;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class amcAdapter extends RecyclerView.Adapter<amcAdapter.adapter>{

    private ArrayList<parent_amc_subscriber> parentamcs = new ArrayList<>();

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());


    public amcAdapter()
    {

    }

    @NonNull
    @Override
    public adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amc_list,parent,false);
        adapter holder = new adapter(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter holder, int position) {
        holder.key.setText(parentamcs.get(position).getKey());
        holder.title.setText(parentamcs.get(position).getTitle());
        holder.firstservice.setText(parentamcs.get(position).getFirstservice());
        holder.secondservice.setText(parentamcs.get(position).getSecondservice());
        holder.thirdservice.setText(parentamcs.get(position).getThirdservice());
        holder.fourthservice.setText(parentamcs.get(position).getFourthservice());
        if(holder.firstservice.getText().toString().equals("Done"))
        {
            holder.firstservice.setVisibility(View.GONE);
            holder.firstimg.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.firstservice.setVisibility(View.VISIBLE);
            holder.firstimg.setVisibility(View.GONE);
        }
        if(holder.secondservice.getText().toString().equals("Done"))
        {
            holder.secondservice.setVisibility(View.GONE);
            holder.secondimg.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.secondservice.setVisibility(View.VISIBLE);
            holder.secondimg.setVisibility(View.GONE);
        }
        if(holder.thirdservice.getText().toString().equals("Done"))
        {
            holder.thirdservice.setVisibility(View.GONE);
            holder.thirdimg.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.thirdservice.setVisibility(View.VISIBLE);
            holder.thirdimg.setVisibility(View.GONE);
        }
        if(holder.fourthservice.getText().toString().equals("Done"))
        {
            holder.fourthservice.setVisibility(View.GONE);
            holder.fourthimg.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.fourthservice.setVisibility(View.VISIBLE);
            holder.fourthimg.setVisibility(View.GONE);
        }
        String ac_title = parentamcs.get(position).getTitle();
        String id = parentamcs.get(position).getKey();
        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                builder.setTitle(ac_title+"\n");
                builder.setMessage(id+"\n\n");

                final EditText input = new EditText(view.getRootView().getContext());


                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                input.setHint("Minimum 10 character");
                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.length()>=10)
                        {
                            DatabaseReference request = FirebaseDatabase.getInstance().getReference();
//                        String request_id = request.child("Admin").child("AmcSubscriberRequest").child(holder.key.getText().toString()).push().getKey();
                            HashMap<String,Object> customerrequest = new HashMap<>();
                            customerrequest.put("Request",id+"> "+input.getText().toString());
                            request.child("Admin").child("AmcSubscriberRequest").child(mAuth.getCurrentUser().getUid()).push().updateChildren(customerrequest);
                            Toast.makeText(view.getRootView().getContext(), "Request Sended", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(view.getRootView().getContext(), "Request not send, Minimum 10 character should be in request message", Toast.LENGTH_SHORT).show();
                        }
                       }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return parentamcs.size();
    }

    public void setParentamcs(ArrayList<parent_amc_subscriber> parentamcs) {
        this.parentamcs = parentamcs;
        notifyDataSetChanged();
    }

    public static class adapter extends RecyclerView.ViewHolder{


        private TextView title;
        private TextView firstservice;
        private TextView secondservice;
        private TextView thirdservice;
        private TextView fourthservice;
        private ImageView firstimg;
        private ImageView secondimg;
        private ImageView thirdimg;
        private ImageView fourthimg;
        private TextView key;
        private Button request;
        private EditText input;

        public adapter(@NonNull View itemView)
        {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            title = itemView.findViewById(R.id.title);
            firstservice = itemView.findViewById(R.id.first);
            secondservice = itemView.findViewById(R.id.second);
            thirdservice = itemView.findViewById(R.id.third);
            fourthservice = itemView.findViewById(R.id.fourth);
            firstimg = itemView.findViewById(R.id.firstimg);
            secondimg = itemView.findViewById(R.id.secondimg);
            thirdimg = itemView.findViewById(R.id.thirdimg);
            fourthimg = itemView.findViewById(R.id.fourthimg);
            request = itemView.findViewById(R.id.request);
            input = itemView.findViewById(R.id.input);
        }
    }


}
