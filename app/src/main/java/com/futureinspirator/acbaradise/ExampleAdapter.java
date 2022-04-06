package com.futureinspirator.acbaradise;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable {
    private List<ExampleItem> exampleList;
    private List<ExampleItem> exampleListFull;

    class ExampleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        RelativeLayout relative;

        ExampleViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView1 = itemView.findViewById(R.id.text_view1);
            textView2 = itemView.findViewById(R.id.text_view2);
            relative = itemView.findViewById(R.id.relative);

        }
    }

    ExampleAdapter(List<ExampleItem> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,
                parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = exampleList.get(position);

        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.textView1.setText(currentItem.getText1());
        holder.textView2.setText(currentItem.getText2());
        int click = position;
        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(("General service split AC").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),generalservicepage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("General service window AC").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),generalservicepage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("General service cassette AC").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),generalservicepage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                /////////////////

                else if(("Water wash split AC single side").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),waterwashpage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Water wash window AC single side").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),waterwashpage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Water wash casstte AC single side").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),waterwashpage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Water wash split AC 360deg").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),waterwashpage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Water wash cassette AC 360deg").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),waterwashpage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                /////////////////////

                else if(("AMC service").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcpage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                else if(("AMC split AC scheme1 totalspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcsplitacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC split AC scheme1 limitedspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcsplitacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC split AC scheme1 nospare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcsplitacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                else if(("AMC split AC scheme2 totalspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcsplitacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC split AC scheme2 limitedspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcsplitacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC split AC scheme2 nospare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcsplitacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                else if(("AMC split AC scheme3 totalspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcsplitacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC split AC scheme3 limitedspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcsplitacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC split AC scheme3 nospare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcsplitacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                else if(("AMC window AC scheme1 totalspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcwindowacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC window AC scheme1 limitedspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcwindowacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC window AC scheme1 nospare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcwindowacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                else if(("AMC window AC scheme2 totalspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcwindowacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC window AC scheme2 limitedspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcwindowacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC window AC scheme2 nospare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amcwindowacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                //////////////////////////////////////////////////////////////

                else if(("AMC cassette AC scheme1 totalspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amccassetteacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC cassette AC scheme1 limitedspare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amccassetteacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("AMC cassette AC scheme1 nospare").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),amccassetteacschemes.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }


                //////////////////////////////////////////////////////

                else if(("Fault split AC water leak").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault split AC gas leak").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault split AC stabilizer").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault split AC pc board").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault split AC indoor coil").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault split AC outdoor condenser").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault split AC remote").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                //////////////////////////////////////////////////////

                else if(("Fault window AC water leak").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault window AC gas leak").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault window AC stabilizer").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault window AC pc board").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault window AC indoor coil").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault window AC outdoor condenser").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault window AC remote").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                //////////////////////////////////////////////////////

                else if(("Fault cassette AC water leak").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault cassette AC gas leak").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault cassette AC stabilizer").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault cassette AC pc board").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault cassette AC indoor coil").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault cassette AC outdoor condenser").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Fault cassette AC remote").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),faultspage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                ///////////////////////////////////////////////////////////////////////////////////

                else if(("Spares split AC capacitor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares split AC blower").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares split AC outdoor fan").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares split AC indoor motor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares split AC outdoor motor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares split AC swing motor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares split AC outdoor condenser").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares split AC swing flap").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares split AC remote").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                ///////////////////////////////////////////////////////////////////////////////////

                else if(("Spares window AC capacitor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares window AC blower").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares window AC outdoor fan").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares window AC indoor motor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares window AC outdoor motor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares window AC swing motor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares window AC outdoor condenser").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares window AC swing flap").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares window AC remote").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                ///////////////////////////////////////////////////////////////////////////////////

                else if(("Spares cassette AC capacitor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares cassette AC blower").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares cassette AC outdoor fan").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares cassette AC indoor motor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares cassette AC outdoor motor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares cassette AC swing motor").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares cassette AC outdoor condenser").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares cassette AC swing flap").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Spares cassette AC remote").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),sparespage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                ////////////////////////////////////////////////////////////////////////////////

                else if(("Split AC installation").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),installuninstallcombopage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Split AC uninstallation").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),installuninstallcombopage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("Split AC combo").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),installuninstallcombopage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                ////////////////////////////////////////////////////////////////////////////////

                else if(("window AC installation").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),installuninstallcombopage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("window AC uninstallation").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),installuninstallcombopage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("window AC combo").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),installuninstallcombopage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

                ////////////////////////////////////////////////////////////////////////////////

                else if(("cassette AC installation").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),installuninstallcombopage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("cassette AC uninstallation").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),installuninstallcombopage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }
                else if(("cassette AC combo").equals(holder.textView2.getText().toString()))
                {
                    Intent intent = new Intent(view.getRootView().getContext(),installuninstallcombopage.class);
                    view.getContext().startActivity(intent);
                    ((Activity)view.getContext()).finish();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExampleItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ExampleItem item : exampleListFull) {
                    if (item.getText2().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}