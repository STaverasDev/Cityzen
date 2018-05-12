package nyc.c4q.cityzenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nyc.c4q.cityzenapp.data.Projects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Projects projects = new Projects();
        projects.getProjects();

    }
}
