package nyc.c4q.cityzenapp.ui;

import com.google.android.gms.maps.model.LatLng;

public interface ConfirmContract {

    interface View{


    }

    interface Presenter{
        void storeRecord(String projectName, LatLng location);
    }
}
