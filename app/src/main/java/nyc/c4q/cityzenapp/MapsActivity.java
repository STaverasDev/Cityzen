package nyc.c4q.cityzenapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import nyc.c4q.cityzenapp.data.GetProjects;
import nyc.c4q.cityzenapp.recview.ProjectAdapter;
import nyc.c4q.cityzenapp.ui.CollectContract;
import nyc.c4q.cityzenapp.ui.CollectPresenter;
import nyc.c4q.cityzenapp.ui.ConfirmationFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, CollectContract.View {

    private GoogleMap mMap;
    private RecyclerView recyclerView;
    private CollectPresenter presenter;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private ProjectAdapter projectAdapter;
    private LatLng curr;
    private final static int MAP_ZOOM = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        presenter = new CollectPresenter(this,new GetProjects());
        presenter.start();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        showMap();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showProjects();
            }
        }, 2000);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
    }

    @Override
    public void showMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void showProjects() {
        recyclerView = findViewById(R.id.projects_recview);
        projectAdapter = new ProjectAdapter(presenter.getProjectList(),this);
        recyclerView.setAdapter(projectAdapter);

    }

    @Override
    public LatLng getLocation() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);
        } else {
            mMap.setMyLocationEnabled(true);

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations, this can be null.
                            double lat = location.getLatitude();
                            double lng = location.getLongitude();
                            curr = new LatLng(lat, lng);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curr,MAP_ZOOM ));

                            if (location != null) {
                                // Logic to handle location object
                            }
                        }
                    });
        }
        return curr;
    }

    @Override
    public void showDialog(String projectName) {
        Toast.makeText(this, projectName, Toast.LENGTH_SHORT).show();
        ConfirmationFragment fragment = new ConfirmationFragment();
        fragment.show(getFragmentManager(),"ConfirmationFragment");


    }
}
