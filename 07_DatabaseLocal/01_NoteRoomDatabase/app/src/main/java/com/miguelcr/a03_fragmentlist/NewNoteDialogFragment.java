package com.miguelcr.a03_fragmentlist;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.miguelcr.a03_fragmentlist.R;

public class NewNoteDialogFragment extends DialogFragment {
    EditText etTitle, etContent;
    RadioGroup rgColor;
    Switch swFav;
    NoteViewModel noteViewModel;

    public NewNoteDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        noteViewModel = ViewModelProviders
                .of(getActivity())
                .get(NoteViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Include a custom layout in the dialog
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_new_note, null);

        etTitle = view.findViewById(R.id.editTextTitle);
        etContent = view.findViewById(R.id.editTextContent);
        rgColor = view.findViewById(R.id.radioGroupColor);
        swFav = view.findViewById(R.id.switchFavourite);

        builder.setView(view);

        builder.setMessage("Write the information for the new note")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Create the new note
                        String color = "";
                        switch (rgColor.getCheckedRadioButtonId()) {
                            case R.id.radioButtonRed:
                                color = "#f28b82"; break;
                            case R.id.radioButtonGreen:
                                color = "#ccff90"; break;
                            case R.id.radioButtonYellow:
                                color = "#fff475"; break;
                            case R.id.radioButtonBlue:
                                color = "#cbf0f8"; break;
                        }
                        Note newNote = new Note(
                                etTitle.getText().toString(),
                                etContent.getText().toString(),
                                swFav.isChecked(),
                                color
                        );
                        noteViewModel.insert(newNote);

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });



        // Create the AlertDialog object and return it
        return builder.create();
    }


}
