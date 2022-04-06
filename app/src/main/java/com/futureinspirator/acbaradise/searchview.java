package com.futureinspirator.acbaradise;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class searchview extends AppCompatActivity {

    private List<ExampleItem> exampleList;
    private ExampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);

        fillExampleList();
        setUpRecyclerView();


    }
        private void fillExampleList() {
            exampleList = new ArrayList<>();
            exampleList.add(new ExampleItem(R.drawable.productsmark, "General Service", "General service split AC"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "General Service", "General service window AC"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "General Service", "General service cassette AC"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Water Wash", "Water wash split AC single side"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Water Wash", "Water wash window AC single side"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Water Wash", "Water wash casstte AC single side"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Water Wash", "Water wash split AC 360deg"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Water Wash", "Water wash cassette AC 360deg"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC service"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC split AC scheme1 totalspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC split AC scheme1 limitedspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC split AC scheme1 nospare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC split AC scheme2 totalspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC split AC scheme2 limitedspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC split AC scheme2 nospare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC split AC scheme3 totalspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC split AC scheme3 limitedspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC split AC scheme3 nospare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC window AC scheme1 totalspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC window AC scheme1 limitedspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC window AC scheme1 nospare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC window AC scheme2 totalspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC window AC scheme2 limitedspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC window AC scheme2 nospare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC cassette AC scheme1 totalspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC cassette AC scheme1 limitedspare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "AMC", "AMC cassette AC scheme1 nospare"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault split AC water leak"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault split AC gas leak"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault split AC stabilizer"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault split AC pc board"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault split AC indoor coil"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault split AC outdoor condenser"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault split AC remote"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault window AC water leak"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault window AC gas leak"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault window AC stabilizer"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault window AC pc board"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault window AC indoor coil"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault window AC outdoor condenser"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault window AC remote"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault cassette AC water leak"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault cassette AC gas leak"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault cassette AC stabilizer"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault cassette AC pc board"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault cassette AC indoor coil"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault cassette AC outdoor condenser"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Fault", "Fault cassette AC remote"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares split AC capacitor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares split AC blower"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares split AC outdoor fan"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares split AC indoor motor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares split AC outdoor motor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares split AC swing motor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares split AC outdoor condenser"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares split AC swing flap"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares split AC remote"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares window AC capacitor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares window AC blower"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares window AC outdoor fan"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares window AC indoor motor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares window AC outdoor motor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares window AC swing motor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares window AC outdoor condenser"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares window AC swing flap"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares window AC remote"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares cassette AC capacitor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares cassette AC blower"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares cassette AC outdoor fan"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares cassette AC indoor motor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares cassette AC outdoor motor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares cassette AC swing motor"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares cassette AC outdoor condenser"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares cassette AC swing flap"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Spares", "Spares cassette AC remote"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Installation & Uninstallation", "Split AC installation"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Installation & Uninstallation", "Split AC uninstallation"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Installation & Uninstallation", "Split AC combo"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Installation & Uninstallation", "window AC installation"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Installation & Uninstallation", "window AC uninstallation"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Installation & Uninstallation", "window AC combo"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Installation & Uninstallation", "cassette AC installation"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Installation & Uninstallation", "cassette AC uninstallation"));
            exampleList.add(new ExampleItem(R.drawable.productsmark, "Installation & Uninstallation", "cassette AC combo"));


        }

        private void setUpRecyclerView() {
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            adapter = new ExampleAdapter(exampleList);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.example_menu, menu);

            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();

            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });
            return true;
        }
    }