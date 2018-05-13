package nyc.c4q.cityzenapp.ui;

import android.location.Location;

import java.util.List;

import nyc.c4q.cityzenapp.model.Project;

public interface CollectContract {
    interface View{
        void showMap();
        void showProjects();

    }

    interface Presenter{
        void start();
        void getPopulatedList();
        void addRecordToProjectCollection(String collection,Location location);

    }
}
