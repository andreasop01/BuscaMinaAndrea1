package com.avidesousa.buscaminaandrea;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dificultad extends DialogFragment {
    private int dimension=8;
    private int bomba=10;
    private OnDificultad df;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        df=(OnDificultad) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Dificultad");
        ArrayAdapter<String> adapterDificultad=new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_single_choice,
                new String[]{"Principiante","Amateur","Avanzado"}
        );

        builder.setSingleChoiceItems(adapterDificultad, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        dimension=8;
                        bomba=10;
                        break;
                    case 1:
                        dimension=12;
                        bomba=15;
                        break;
                    case 2:
                        dimension=16;
                        bomba=20;
                        break;
                }
            }
        });

        builder.setPositiveButton("Volver", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                df.onDificultad(dimension,bomba);
            }
        });
        return builder.create();
    }

    public interface OnDificultad{
        public void onDificultad(int dimension,int bomba);

    }
}
