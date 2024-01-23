package com.avidesousa.buscaminaandrea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Dificultad.OnDificultad, DialogBomba.OnBombaSelect {

    public GridLayout grid;
    public int nBombas=10;
    public int tamañoTablero=8;
    public ConstraintLayout constraintLayout;
    public Tablero t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout=findViewById(R.id.layout_principal);
        constraintLayout.post(new Runnable() {
            @Override
            public void run() {
                jugar();
            }
        });
    }

    public void jugar(){
        int width=constraintLayout.getWidth();
        int height=constraintLayout.getHeight();

        grid=findViewById(R.id.gridBotones);
        GridLayout.LayoutParams params=new GridLayout.LayoutParams();
        params.setMargins(0,0,0,0);
        params.height=ViewGroup.LayoutParams.MATCH_PARENT;
        params.width=ViewGroup.LayoutParams.MATCH_PARENT;

        grid.setLayoutParams(params);
        grid.setRowCount(tamañoTablero);
        grid.setColumnCount(tamañoTablero);

        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width/tamañoTablero,height/tamañoTablero);
        layoutParams.setMargins(0,0,0,0);

        t=new Tablero(tamañoTablero,layoutParams,nBombas, getApplicationContext());

        for (int fila=0;fila<tamañoTablero;fila++){
            for(int colum=0;colum<tamañoTablero;colum++){
                grid.addView(
                       t.getCasillas()[fila][colum]
                );
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.mnBomba){
            DialogBomba db = new DialogBomba();
            db.show(getSupportFragmentManager(),"selectBomba");
        }
        if(item.getItemId()==R.id.mnReiniciar){
            grid.removeAllViews();
            jugar();
        }
        if(item.getItemId()==R.id.mnDificultad){
           Dificultad dif=new Dificultad();
           dif.show(getSupportFragmentManager(),"dificultad");
        }
        if(item.getItemId()==R.id.mnInfo){
            Info info=new Info();
            info.show(getSupportFragmentManager(),"informacion");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDificultad(int dimension, int bomba) {
        this.tamañoTablero=dimension;
        this.nBombas=bomba;
        grid.removeAllViews();
        jugar();
    }

    @Override
    public void onBombaSelect(int bomba) {
        t.setBomba(bomba);
    }
}