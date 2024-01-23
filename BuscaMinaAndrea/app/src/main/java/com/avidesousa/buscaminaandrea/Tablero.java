package com.avidesousa.buscaminaandrea;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Tablero {
    private Casilla casillas[][];
    private int tamañoMatriz;
    private int nBombas;
    private Context con;
    private LinearLayout.LayoutParams layoutParams;
    private ArrayList<int[]> posicionPicada;
    private int banderasColocadas;
    private boolean juegoCurso;
    private int bombas[] = {
            R.drawable.bomba,
            R.drawable.bomba1,
            R.drawable.bomba2
    };
    private int bombaSeleccionada;

    public Tablero(int tamañoMatriz, LinearLayout.LayoutParams layoutParams, int nBombas, Context con) {
        this.tamañoMatriz = tamañoMatriz;
        this.casillas=new Casilla[tamañoMatriz][tamañoMatriz];
        this.layoutParams = layoutParams;
        this.nBombas=nBombas;
        this.con=con;
        this.posicionPicada=new ArrayList<>();
        this.banderasColocadas=0;
        this.juegoCurso=true;
        this.bombaSeleccionada=0;
        crearTablero();
        asignarBombas();
        asignarNumero();
    }

    public void ganar(){
        Toast.makeText(con,"GANASTE!",Toast.LENGTH_LONG).show();
        juegoCurso=false;
    }

    public void perder(){
        Toast.makeText(con,"PERDISTE =(",Toast.LENGTH_LONG).show();
        for (int fila=0;fila<tamañoMatriz;fila++){
            for(int colum=0;colum<tamañoMatriz;colum++){
                if(casillas[fila][colum].isMina()){
                    casillas[fila][colum].setBackground(con.getDrawable(elegirBomba()));
                }
            }
        }
        juegoCurso=false;
    }
    public void crearTablero(){
        for(int fila=0;fila<tamañoMatriz;fila++){
            for(int colum=0;colum<tamañoMatriz;colum++){
                Casilla c=new Casilla(con,fila,colum,false);
                c.setLayoutParams(layoutParams);
                c.setBackground(con.getDrawable(R.drawable.cell1));

                c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!juegoCurso){
                            return;
                        }
                        Casilla c=(Casilla)v;
                        picarCasilla(c.getPosiFila(),c.getPosiColumna());

                    }
                });

                c.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(!juegoCurso){
                            return true;
                        }
                        Casilla c=(Casilla)v;
                        if(!c.isMina()){
                            perder();
                        }else{
                            c.setBackground(con.getDrawable(R.drawable.bandera));
                            banderasColocadas++;
                            if(banderasColocadas==nBombas){
                                ganar();
                            }
                        }
                        return true;
                    }
                });
                casillas[fila][colum]=c;
            }
        }
    }

    public void picarCasilla(int fila, int colum){
        this.posicionPicada.add(new int[]{fila,colum});

        if(casillas[fila][colum].isMina()){
            perder();

            return;
        }else{
            casillas[fila][colum].setBackground(con.getDrawable(R.drawable.cell2));
        }

        if(casillas[fila][colum].numeros>0){
            casillas[fila][colum].setText(casillas[fila][colum].numeros+"");
            return;
        }

        for(int f=fila-1;f<=fila+1;f++){
            for(int c=colum-1;c<=colum+1;c++){

                boolean seguirPicando=true;
                for(int[] posiciones:posicionPicada){
                    if(posiciones[0]==f && posiciones[1]==c){
                        seguirPicando=false;
                    }
                }
                if(f<0||f>=tamañoMatriz || c<0||c>=tamañoMatriz){
                    continue;
                }
                if(seguirPicando){
                    picarCasilla(f,c);
                }

            }
        }
    }

    public void asignarNumero(){
        for(int fila=0;fila<tamañoMatriz;fila++){
            for(int colum=0;colum<tamañoMatriz;colum++){

                if(casillas[fila][colum].isMina()){
                    contarMinas(fila,colum);
                }
            }
        }
    }

    public void contarMinas(int fila, int colum){

        for(int f=fila-1;f<=fila+1;f++){
            for(int c=colum-1;c<=colum+1;c++){

                if(f<0||f>=tamañoMatriz || c<0||c>=tamañoMatriz){
                    continue;
                }
                if(f == fila && c==colum){
                    continue;
                }

                casillas[f][c].numeros++;
            }
        }
    }
    public void asignarBombas(){
        int bombasPuestas=0;

        while(bombasPuestas!=nBombas){
            int fila=(int)(Math.random()*tamañoMatriz);
            int colum=(int)(Math.random()*tamañoMatriz);

            if(!casillas[fila][colum].isMina()){
                casillas[fila][colum].setMina(true);
                bombasPuestas++;
            }

        }
    }

    public int elegirBomba(){
        return bombas[bombaSeleccionada];
    }

    public void setBomba(int bomba){
        bombaSeleccionada=bomba;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }
}
