package nyc.c4q.cityzenapp.ui;

import android.location.Location;
import android.util.Log;

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

    public List<Project> getProjectList() {
        return projectList;
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
    public void addRecordToProjectCollection(String collection, Location location) {

    }
}
