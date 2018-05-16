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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        presenter = new ConfirmPresenter();

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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
