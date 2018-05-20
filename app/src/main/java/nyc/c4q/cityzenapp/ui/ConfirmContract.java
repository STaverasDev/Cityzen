package nyc.c4q.cityzenapp.ui;

import com.google.android.gms.maps.model.LatLng;

public interface ConfirmContract {

    interface View{


    }

    interface Presenter{
        void start();
        void setProjectName(String projectName);
        void setLocation(LatLng location);
        void storeRecord();
    }
}
