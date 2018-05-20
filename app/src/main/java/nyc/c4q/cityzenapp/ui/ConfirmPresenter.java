package nyc.c4q.cityzenapp.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import nyc.c4q.cityzenapp.model.Project;

import static android.support.constraint.Constraints.TAG;

public class ConfirmPresenter implements ConfirmContract.Presenter {
    private final static String PROJECTS_COLLECTION = "projects";
    private final static String RECORDS_COLLECTION = "records";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String projectName;
    private DocumentReference docRef;
    private LatLng location;

    @Override
    public void start() {

    }

    @Override
    public void setProjectName(String projectName) {
        this.projectName = projectName;

    }

    @Override
    public void setLocation(LatLng location) {
        this.location = location;

    }

    @Override
    public void storeRecord() {

        Double latitude = location.latitude;
        Double longitude = location.longitude;

        Map<String, Object> data = new HashMap<>();
        data.put("lat", latitude);
        data.put("lng", longitude);

        DocumentReference messageRef = db
                .collection(PROJECTS_COLLECTION).document(projectName)
                .collection(RECORDS_COLLECTION).document();

        messageRef.set(data, SetOptions.merge());

    }

}
