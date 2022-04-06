package com.futureinspirator.acbaradise;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class checkout extends AppCompatActivity implements LocationListener, OnMapReadyCallback, PaymentResultListener {


    GoogleMap map;
    private int payment = 0;
    private String stringpayment = "0";
    private Button Confirm;
    private double orderLat;
    private double orderLng;
    private int amount = 0;
    boolean check = true;
    private String date;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    DatabaseReference onpaid = FirebaseDatabase.getInstance().getReference();
    private String Orderkey = onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").push().getKey();
    private  String Number;
    private String address;
    private String totalquantity,totalprice,totaldiscount;


    LocationManager locationManager;

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;


    DatabaseReference mapping = FirebaseDatabase.getInstance().getReference();

    DatabaseReference num = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("Number");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mapping.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                totalprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                totalquantity = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALQUANTITY").getValue().toString();
                totaldiscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALSAVED").getValue().toString();

                amount = Integer.parseInt(totalprice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        stringpayment = getIntent().getExtras().get("payment").toString();

        num.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Number = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Confirm = findViewById(R.id.Confirm_Location);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                payment = Integer.parseInt(stringpayment);
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat format = new SimpleDateFormat("EEEE, d MMM yyyy, hh:mm aa");

                date = format.format(calendar.getTime()).toString();

                if(payment==0) {
                    checkout();
                }
                if(payment ==1)
                {
                    toadmin();
                    finish();
                }
            }
        });



        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        client = LocationServices.getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(checkout.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(checkout.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 101);


        }



        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationEnabled();
        getLocation();
    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location!=null){
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {

                            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            int pin = Integer.parseInt(addresses.get(0).getPostalCode());
                            address = addresses.get(0).getAddressLine(0);

                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng)
                                    .title("I am here");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                            googleMap.addMarker(options);

                            int jai = Integer.parseInt(addresses.get(0).getPostalCode());

                            String peru = Integer.toString(pin);


                            mapping.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.child("Availableareas").child(peru).exists()) {

                                        Confirm.setEnabled(true);
                                        Confirm.setVisibility(View.VISIBLE);
                                        orderLat = latLng.latitude;
                                        orderLng = latLng.longitude;

                                    } else {
                                        Confirm.setEnabled(false);
                                        Confirm.setVisibility(View.GONE);

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });





                        }
                    });



                }

            }

        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }



    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(checkout.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                map.clear();
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
                map.addMarker(markerOptions);

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int pin = Integer.parseInt(addresses.get(0).getPostalCode());

                address = addresses.get(0).getAddressLine(0);

                String peru = Integer.toString(pin);


                mapping.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child("Availableareas").child(peru).exists()){

                            Confirm.setEnabled(true);
                            Confirm.setVisibility(View.VISIBLE);
                            orderLat = latLng.latitude;
                            orderLng = latLng.longitude;

                        }else {
                            Confirm.setEnabled(false);
                            Confirm.setVisibility(View.GONE);

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });



    }


    public void checkout(){
        Checkout checkout = new Checkout();

        checkout.setKeyID("rzp_test_wic5QWXwG8LIPV");

        JSONObject object = new JSONObject();

        try {
            object.put("name","AC Baradise");

            object.put("description","Test Payment");

            object.put("theme.color","#0093DD");

            object.put("currency","INR");

            object.put("amount",amount*100);

            object.put("prefill.contact",mAuth.getCurrentUser().getUid());

            object.put("prefill.email","jaideepasivakumar02@gmail.com");

            checkout.open(checkout.this,object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onPaymentSuccess(String s)
    {
        toadmin();
        finish();
    }

    @Override
    public void onPaymentError(int i, String s) {


        Toast.makeText(checkout.this, "PAYMENT FAILED", Toast.LENGTH_SHORT).show();
        finish();

    }

    private void toadmin()
    {

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("GeneralService").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> hashvalue = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String Stringvalue = dataSnapshot.getValue().toString();
                    int value = Integer.parseInt(Stringvalue);
                    if(value != 0)
                    {
                        hashvalue.put("GeneralService > "+dataSnapshot.getKey().toString(),dataSnapshot.getValue().toString());
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("GeneralService").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(hashvalue);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(hashvalue);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("WaterWash").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> hashvalue = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String Stringvalue = dataSnapshot.getValue().toString();
                    int value = Integer.parseInt(Stringvalue);
                    if(value != 0)
                    {
                        hashvalue.put("WaterWash > "+dataSnapshot.getKey().toString(),dataSnapshot.getValue().toString());
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("WaterWash").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(hashvalue);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(hashvalue);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("SplitAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Spares > SplitAC > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("SplitAC").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("WindowAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Spares > WindowAC > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("WindowAC").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("CassetteAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Spares > CassetteAC > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("CassetteAC").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("SplitAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Faults > SplitAC > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("SplitAC").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("WindowAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Faults > WindowAC > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("WindowAC").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("CassetteAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Faults > CassetteAC > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("CassetteAC").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("SplitAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {

                        products.put("Installuninstall > SplitAC > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("SplitAC").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("WindowAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {

                        products.put("Installuninstall > WindowAC > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("WindowAC").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("CassetteAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Installuninstall > CassetteAC > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("CassetteAC").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withspare").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    if(stringkey.equals("Totalspare")||stringkey.equals("Limitedspare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > SplitAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("SplitAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme2 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withspare").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    if(stringkey.equals("Totalspare")||stringkey.equals("Limitedspare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > SplitAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("SplitAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme3 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withspare").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    if(stringkey.equals("Totalspare")||stringkey.equals("Limitedspare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > SplitAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("SplitAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme1 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withoutspare").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    String stringkey = dataSnapshot.getKey().toString();
                    if(stringkey.equals("Nospare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > SplitAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("SplitAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme2 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withoutspare").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    String stringkey = dataSnapshot.getKey().toString();
                    if(stringkey.equals("Nospare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > SplitAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("SplitAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme3 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withoutspare").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    String stringkey = dataSnapshot.getKey().toString();
                    if(stringkey.equals("Nospare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > SplitAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("SplitAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > WindowAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withspare").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    if(stringkey.equals("Totalspare")||stringkey.equals("Limitedspare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > WindowAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("WindowAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > WindowAC > Scheme2 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withspare").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    if(stringkey.equals("Totalspare")||stringkey.equals("Limitedspare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > WindowAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("WindowAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > WindowAC > Scheme1 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withoutspare").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    String stringkey = dataSnapshot.getKey().toString();
                    if(stringkey.equals("Nospare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > WindowAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("WindowAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > WindowAC > Scheme2 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withoutspare").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    String stringkey = dataSnapshot.getKey().toString();
                    if(stringkey.equals("Nospare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > WindowAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("WindowAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > CassetteAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withspare").child(dataSnapshot.getKey().toString()).setValue("0");

                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    if(stringkey.equals("Totalspare")||stringkey.equals("Limitedspare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > CassetteAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("CassetteAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > CassetteAC > Scheme1 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withoutspare").child(dataSnapshot.getKey().toString()).setValue("0");
                    }
                    onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(products);
                    onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(products);
                    String stringkey = dataSnapshot.getKey().toString();
                    if(stringkey.equals("Nospare"))
                    {
                        for(int i=0;i<quantity;i++)
                        {
                            String key = onpaid.child("Admin").child("AmcSubscriber").push().getKey();
                            HashMap<String,Object> asd = new HashMap<>();
                            asd.put("AMC > CassetteAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                            amcsubscriber("CassetteAC "+stringkey,key);
                            onpaid.child("Admin").child("AmcSubscriber").child(key).updateChildren(asd);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                HashMap<String,Object> tozero = new HashMap<>();
                tozero.put("TOTALPRICE","0");
                tozero.put("TOTALQUANTITY","0");
                tozero.put("TOTALSAVED","0");
                onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(tozero);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        HashMap<String,Object> admin = new HashMap<>();
        admin.put("Latitude",String.valueOf(orderLat));
        admin.put("Longitude",String.valueOf(orderLng));
        if(payment==1)
        {
            admin.put("Payment","Cash On Delivery");
        }
        else if(payment==0)
        {
            admin.put("Payment","Paid");
        }
        onpaid.child("Admin").child("Userbooking").child(mAuth.getCurrentUser().getUid()).child(Orderkey).updateChildren(admin);

        HashMap<String, Object> order = new HashMap<>();
        order.put("Payment",stringpayment);
        order.put("Address",address);
        order.put("Totalsaved",totaldiscount);
        order.put("Totalitems",totalquantity);
        order.put("Charge","Free delivery");
        order.put("Grandtotal",totalprice);
        order.put("Date",date);
        order.put("Number",Number);
        order.put("Discount", totaldiscount);
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(Orderkey).updateChildren(order);
        Toast.makeText(this, "Your order placed Successfully", Toast.LENGTH_SHORT).show();
    }

    private String amcsubscriber(String title,String key) {

        String first,second,third,fourth;
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat checkingformat = new SimpleDateFormat("a");
        SimpleDateFormat day = new SimpleDateFormat("EEEE");
        SimpleDateFormat hour = new SimpleDateFormat("h");


        Calendar check = Calendar.getInstance();
        String checking = checkingformat.format(check.getTime()).toLowerCase();
        String daychecking = day.format(check.getTime()).toLowerCase();
        String hourcheckig = hour.format(check.getTime());
        int inthourchecking = Integer.parseInt(hourcheckig);


        if(!(daychecking.equals("sunday"))  &&  checking.equals("am"))
        {
            Calendar firstDate = Calendar.getInstance();
            first = format.format(firstDate.getTime()).toUpperCase();

            Calendar secondDate = Calendar.getInstance();
            secondDate.add(Calendar.MONTH, 4);
            second = format.format(secondDate.getTime()).toUpperCase();

            Calendar thirdDate = Calendar.getInstance();
            thirdDate.add(Calendar.MONTH, 8);
            third = format.format(thirdDate.getTime()).toUpperCase();

            Calendar fourthDate = Calendar.getInstance();
            fourthDate.add(Calendar.MONTH, 12);
            fourth = format.format(fourthDate.getTime()).toUpperCase();
        }
        else if(!(daychecking.equals("sunday"))   &&  checking.equals("pm")    &&    (inthourchecking<5||inthourchecking==12))
        {
            Calendar firstDate = Calendar.getInstance();
            first = format.format(firstDate.getTime()).toUpperCase();

            Calendar secondDate = Calendar.getInstance();
            secondDate.add(Calendar.MONTH, 4);
            second = format.format(secondDate.getTime()).toUpperCase();

            Calendar thirdDate = Calendar.getInstance();
            thirdDate.add(Calendar.MONTH, 8);
            third = format.format(thirdDate.getTime()).toUpperCase();

            Calendar fourthDate = Calendar.getInstance();
            fourthDate.add(Calendar.MONTH, 12);
            fourth = format.format(fourthDate.getTime()).toUpperCase();
        }
        else
        {
            Calendar firstDate = Calendar.getInstance();
            firstDate.add(Calendar.DATE,1);
            first = format.format(firstDate.getTime()).toUpperCase();

            Calendar secondDate = Calendar.getInstance();
            secondDate.add(Calendar.MONTH, 4);
            secondDate.add(Calendar.DATE,1);
            second = format.format(secondDate.getTime()).toUpperCase();

            Calendar thirdDate = Calendar.getInstance();
            thirdDate.add(Calendar.MONTH, 8);
            thirdDate.add(Calendar.DATE,1);
            third = format.format(thirdDate.getTime()).toUpperCase();

            Calendar fourthDate = Calendar.getInstance();
            fourthDate.add(Calendar.MONTH, 12);
            fourthDate.add(Calendar.DATE,1);
            fourth = format.format(fourthDate.getTime()).toUpperCase();

        }

        HashMap<String,Object> list = new HashMap<>();
        list.put("Title",title);
        list.put("FirstService",first);
        list.put("SecondService",second);
        list.put("ThirdService",third);
        list.put("FourthService",fourth);
        list.put("Key",key);
        onpaid.child("Users").child(mAuth.getCurrentUser().getUid()).child("amc_subscriber").child(key).updateChildren(list);

        return key;

    }

}