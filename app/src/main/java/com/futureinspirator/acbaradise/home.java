package com.futureinspirator.acbaradise;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class home extends Fragment implements LocationListener {


    GoogleMap map;
    private double orderLat;
    private double orderLng;
    private String currentaddress;
    LocationManager locationManager;

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    DatabaseReference mapping = FirebaseDatabase.getInstance().getReference();

    private CardView gscardview,wwcardview,amccardview,sparecardview,faultcardview,installuninstallcardview;
    private CardView summarycart;
    private RelativeLayout relative1,relative2;
    private int ttotal=0;

    private BottomNavigationView bottomNavigationView;
    private RelativeLayout relative;
    private ImageView marker;
    private TextView currentlocationtxt,locationtxt;
    private ImageView permium1;
    private FrameLayout searchView;

    private FrameLayout whyacb,acbblogs;

    private ImageSlider imageSlider;

    private DatabaseReference root  = FirebaseDatabase.getInstance().getReference();

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_home, container, false);

//        closekeyboard();

        searchView = v.findViewById(R.id.searchView);

        client = LocationServices.getFusedLocationProviderClient(getActivity());

        permium1 = v.findViewById(R.id.permium1);

        relative1 = v.findViewById(R.id.relative1);
        relative2 = v.findViewById(R.id.relative2);
        marker = v.findViewById(R.id.marker);
        currentlocationtxt = v.findViewById(R.id.currentlocationtxt);
        locationtxt = v.findViewById(R.id.locationtxt);


        imageSlider = v.findViewById(R.id.slider1);
        whyacb = v.findViewById(R.id.whyacb);
        acbblogs = v.findViewById(R.id.acbblog);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getRootView().getContext(), searchview.class);
                startActivity(intent);
            }
        });

        permium1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getRootView().getContext(), premium.class);
                startActivity(intent);

            }
        });

        whyacb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getRootView().getContext(), acb_blogs.class);
                startActivity(intent);

            }
        });

        acbblogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getRootView().getContext(), acb_blogs.class);
                startActivity(intent);
            }
        });

        summarycart = v.findViewById(R.id.summarycart);

        summarycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getRootView().getContext(),summary.class);
                startActivity(intent);
            }
        });

        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getRootView().getContext(), map.class);
                startActivity(intent);
            }
        });
        currentlocationtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getRootView().getContext(), map.class);
                startActivity(intent);
            }
        });
        locationtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getRootView().getContext(), map.class);
                startActivity(intent);
            }
        });


        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ArrayList<SlideModel> imagelist = new ArrayList<SlideModel>();

        imagelist.add(new SlideModel(R.drawable.sliderimagetemp2, null));
        imagelist.add(new SlideModel(R.drawable.sliderimagetemp1, null));
        imagelist.add(new SlideModel(R.drawable.sliderimagetemp, null));
        imagelist.add(new SlideModel(R.drawable.sliderimagetemp3, null));
        imagelist.add(new SlideModel(R.drawable.sliderimagetemp4, null));

        imageSlider.setImageList(imagelist);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String total = snapshot.child("Products").child("GeneralService").child("Price").child("SplitAC").getValue().toString();
                String address = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Address").getValue().toString();
                locationtxt.setText(address);
                ttotal = Integer.parseInt(total);
                if (ttotal != 0) {
                    relative2.setVisibility(View.GONE);
                    relative1.setVisibility(View.VISIBLE);
                    requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
                if(address.equals("I'm Here"))
                {
                    locationEnabled();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("GeneralService").exists())) {
                    HashMap<String, Object> gsadditems = new HashMap<>();
                    gsadditems.put("CassetteAC", "0");
                    gsadditems.put("SplitAC", "0");
                    gsadditems.put("WindowAC", "0");
                    gsadditems.put("SplitACTOTAL", "0");
                    gsadditems.put("CassetteACTOTAL", "0");
                    gsadditems.put("WindowACTOTAL", "0");
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("GeneralService").updateChildren(gsadditems);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("WaterWash").exists())) {
                    HashMap<String, Object> wwadditems = new HashMap<>();
                    wwadditems.put("CassetteSSAC", "0");
                    wwadditems.put("SplitSSAC", "0");
                    wwadditems.put("WindowSSAC", "0");
                    wwadditems.put("SplitSSACTOTAL", "0");
                    wwadditems.put("CassetteSSACTOTAL", "0");
                    wwadditems.put("Cassette360AC", "0");
                    wwadditems.put("Split360AC", "0");
                    wwadditems.put("Split360ACTOTAL", "0");
                    wwadditems.put("Cassette360ACTOTAL", "0");
                    wwadditems.put("WindowSSACTOTAL", "0");
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("WaterWash").updateChildren(wwadditems);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("SplitAC").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("WindowAC").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("CassetteAC").exists())) {
                    HashMap<String, Object> sparessplitadditems = new HashMap<>();
                    sparessplitadditems.put("Blower", "0");
                    sparessplitadditems.put("BlowerTOTAL", "0");
                    sparessplitadditems.put("Capacitor", "0");
                    sparessplitadditems.put("CapacitorTOTAL", "0");
                    sparessplitadditems.put("Indoorcoil", "0");
                    sparessplitadditems.put("IndoorcoilTOTAL", "0");
                    sparessplitadditems.put("Indoormotor", "0");
                    sparessplitadditems.put("IndoormotorTOTAL", "0");
                    sparessplitadditems.put("Outdoorcondenser", "0");
                    sparessplitadditems.put("OutdoorcondenserTOTAL", "0");
                    sparessplitadditems.put("Outdoorfan", "0");
                    sparessplitadditems.put("OutdoorfanTOTAL", "0");
                    sparessplitadditems.put("Outdoormotor", "0");
                    sparessplitadditems.put("OutdoormotorTOTAL", "0");
                    sparessplitadditems.put("Remote", "0");
                    sparessplitadditems.put("RemoteTOTAL", "0");
                    sparessplitadditems.put("Swingflap", "0");
                    sparessplitadditems.put("SwingflapTOTAL", "0");
                    sparessplitadditems.put("Swingmotor", "0");
                    sparessplitadditems.put("SwingmotorTOTAL", "0");
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("SplitAC").updateChildren(sparessplitadditems);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("WindowAC").updateChildren(sparessplitadditems);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("CassetteAC").updateChildren(sparessplitadditems);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("SplitAC").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("WindowAC").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("CassetteAC").exists())) {
                    HashMap<String, Object> faultssplitadditems = new HashMap<>();
                    faultssplitadditems.put("Gasleak", "0");
                    faultssplitadditems.put("GasleakTOTAL", "0");
                    faultssplitadditems.put("Indoorcoil", "0");
                    faultssplitadditems.put("IndoorcoilTOTAL", "0");
                    faultssplitadditems.put("Outdoorcondenser", "0");
                    faultssplitadditems.put("OutdoorcondenserTOTAL", "0");
                    faultssplitadditems.put("Pcboard", "0");
                    faultssplitadditems.put("PcboardTOTAL", "0");
                    faultssplitadditems.put("Remote", "0");
                    faultssplitadditems.put("RemoteTOTAL", "0");
                    faultssplitadditems.put("Stabilizer", "0");
                    faultssplitadditems.put("StabilizerTOTAL", "0");
                    faultssplitadditems.put("Waterleak", "0");
                    faultssplitadditems.put("WaterleakTOTAL", "0");
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("SplitAC").updateChildren(faultssplitadditems);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("WindowAC").updateChildren(faultssplitadditems);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("CassetteAC").updateChildren(faultssplitadditems);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("SplitAC").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("WindowAC").exists() ||
                                !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("CassetteAC").exists()))) {
                    HashMap<String, Object> installsplitadditems = new HashMap<>();
                    installsplitadditems.put("Combo", "0");
                    installsplitadditems.put("ComboTOTAL", "0");
                    installsplitadditems.put("Install", "0");
                    installsplitadditems.put("InstallTOTAL", "0");
                    installsplitadditems.put("Uninstall", "0");
                    installsplitadditems.put("UninstallTOTAL", "0");
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("SplitAC").updateChildren(installsplitadditems);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("WindowAC").updateChildren(installsplitadditems);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("CassetteAC").updateChildren(installsplitadditems);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").exists()) ||
                        !(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").exists())) {
                    HashMap<String, Object> withoutspare = new HashMap<>();
                    withoutspare.put("Nospare", "0");
                    withoutspare.put("NospareTOTAL", "0");

                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withoutspare").updateChildren(withoutspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withoutspare").updateChildren(withoutspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withoutspare").updateChildren(withoutspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withoutspare").updateChildren(withoutspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withoutspare").updateChildren(withoutspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withoutspare").updateChildren(withoutspare);

                    HashMap<String, Object> withspare = new HashMap<>();
                    withspare.put("Limitedspare", "0");
                    withspare.put("LimitedspareTOTAL", "0");
                    withspare.put("Totalspare", "0");
                    withspare.put("TotalspareTOTAL", "0");

                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withspare").updateChildren(withspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withspare").updateChildren(withspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withspare").updateChildren(withspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withspare").updateChildren(withspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withspare").updateChildren(withspare);
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withspare").updateChildren(withspare);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("GeneralService").exists())) {
                    HashMap<String, Object> gsadditems = new HashMap<>();
                    gsadditems.put("CassetteAC", "0");
                    gsadditems.put("SplitAC", "0");
                    gsadditems.put("WindowAC", "0");
                    gsadditems.put("SplitACTOTAL", "0");
                    gsadditems.put("CassetteACTOTAL", "0");
                    gsadditems.put("WindowACTOTAL", "0");
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("GeneralService").updateChildren(gsadditems);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        gscardview = v.findViewById(R.id.gscardview);
        wwcardview = v.findViewById(R.id.wwcardview);
        amccardview = v.findViewById(R.id.amccardview);
        sparecardview = v.findViewById(R.id.sparecardview);
        faultcardview = v.findViewById(R.id.faultcardview);
        installuninstallcardview = v.findViewById(R.id.installuninstallcardview);

        gscardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getRootView().getContext(), generalservicepage.class);
                startActivity(intent);
            }
        });

        wwcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getRootView().getContext(), waterwashpage.class);
                startActivity(intent);
            }
        });

        amccardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getRootView().getContext(), amcpage.class);
                startActivity(intent);
            }
        });

        sparecardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getRootView().getContext(), sparespage.class);
                startActivity(intent);
            }
        });

        faultcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getRootView().getContext(), faultspage.class);
                startActivity(intent);
            }
        });

        installuninstallcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getRootView().getContext(), installuninstallcombopage.class);
                startActivity(intent);
            }
        });


        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            getCurrentLocation();

        }

        return v;
    }

//    private void closekeyboard()
//    {
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager).activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }


    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

         LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

         if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {

            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();

                    if(location != null)
                    {

                        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                        List<Address> addresses = null;
                        try {
                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        String address = addresses.get(0).getAddressLine(0);

                        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Address").setValue(address);

                    }
                }
            });

        }
    }

    private void locationEnabled() {
        LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
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
            new AlertDialog.Builder(getActivity())
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


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}