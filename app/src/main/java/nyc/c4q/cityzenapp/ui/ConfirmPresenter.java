package nyc.c4q.cityzenapp.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import nyc.c4q.cityzenapp.model.Project;

import static android.support.constraint.Constraints.TAG;

public class ConfirmPresenter implements ConfirmContract.Presenter {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String projectName;
    private DocumentReference docRef;

    @Override
    public void storeRecord(String projectName, LatLng location) {
        docRef = db.collection("cities").document(projectName);

        db.collection("projects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }

                });


    }


}
