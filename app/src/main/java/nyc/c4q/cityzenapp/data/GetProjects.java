package nyc.c4q.cityzenapp.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.cityzenapp.model.Project;

public class GetProjects {
    private final static String TAG = "PROJECTS";
    private List<Project> projectList = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void getProjectsList() {
        db.collection("projects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Project project = new Project();
                                project.setImg(document.get("img").toString());
                                Log.d(TAG, document.get("img").toString());
                                project.setName(document.get("name").toString());
                                projectList.add(project);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            populateList(projectList);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }

                });
    }

    private void populateList(List<Project> projects) {
        this.projectList = projects;
    }

    public List<Project> getPopulatedList(){
        return this.projectList;
    }

}
