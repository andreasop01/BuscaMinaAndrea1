package com.avidesousa.buscaminaandrea;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogBomba extends DialogFragment {
    private OnBombaSelect bombaSelect;
    private Bomba bomba[]={
            new Bomba("ClasicBom",R.drawable.bomba),
            new Bomba("NewBomba",R.drawable.bomba1),
            new Bomba("Explosion",R.drawable.bomba2)
    };
    private int bombaSelecionada;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bombaSelect=(OnBombaSelect) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Elige bomba");
        BombaAdapter bombaAdapter=new BombaAdapter(
                getActivity(),
                R.layout.fila_bomba,
                bomba
        );

        builder.setAdapter(bombaAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bombaSelect.onBombaSelect(which);
            }
        });

        return builder.create();
    }

    public class BombaAdapter extends ArrayAdapter<Bomba> {
        private Bomba bomba[];
        public BombaAdapter(@NonNull Context context, int resource, @NonNull Bomba[] bombs) {
            super(context, resource, bombs);
            this.bomba = bombs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position, convertView, parent);
        }

        public View crearFila(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater miInflador = getLayoutInflater();
            View miFila = miInflador.inflate(
                    R.layout.fila_bomba,
                    parent,
                    false
            );

            TextView nombre = miFila.findViewById(R.id.txtNombre);
            ImageView img = miFila.findViewById(R.id.imgBomba);

            nombre.setText(
                    bomba[position].getNombre()
            );
            img.setImageResource(
                    bomba[position].getImagen()
            );

            return miFila;
        }
    }

    public interface OnBombaSelect{
        public void onBombaSelect(int bomba);
    }
}
