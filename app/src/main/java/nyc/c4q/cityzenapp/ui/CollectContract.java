package nyc.c4q.cityzenapp.ui;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import nyc.c4q.cityzenapp.model.Project;

public interface CollectContract {
    interface View{
        void showMap();
        void showProjects();
        LatLng getLocation();
        void showDialog(String projectName);

    }

    interface Presenter{
        void start();
        void getPopulatedList();
        void storeLocation(LatLng location);
        void addRecordToProjectCollection(String collection,Location location);

    }
}
