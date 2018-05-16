package nyc.c4q.cityzenapp.ui;

import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import nyc.c4q.cityzenapp.data.GetProjects;
import nyc.c4q.cityzenapp.model.Project;

public class CollectPresenter implements CollectContract.Presenter {

    private CollectContract.View view;
    private List<Project> projectList;
    private GetProjects getProjects;


    public CollectPresenter(CollectContract.View view, GetProjects projects) {
        this.view = view;
        this.getProjects = projects;

    }

    @Override
    public void start() {
        getProjects.getProjectsList();
        getPopulatedList();

    }

    @Override
    public void getPopulatedList() {
        this.projectList = getProjects.getPopulatedList();

    }


    @Override
    public void storeLocation(LatLng latLng) {


    }

    @Override
    public void addRecordToProjectCollection(String collection, Location location) {


    }

    public List<Project> getProjectList() {
        return projectList;
    }


}
