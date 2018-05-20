package nyc.c4q.cityzenapp.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import nyc.c4q.cityzenapp.R;

public class ConfirmationFragment extends DialogFragment {

    private ConfirmPresenter presenter;
    private String projectName;
    private LatLng location;
    private Bundle args;




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        presenter = new ConfirmPresenter();
        args = getArguments();

        Double lat = args.getDouble("lat");
        Double lng = args.getDouble("lng");
        presenter.setProjectName(args.get("project").toString());
        presenter.setLocation(new LatLng(lat, lng));



        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.storeRecord();
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();
    }

}
