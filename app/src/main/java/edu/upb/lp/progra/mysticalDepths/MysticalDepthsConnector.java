package edu.upb.lp.progra.mysticalDepths;

import java.util.Random;

import edu.upb.lp.progra.adapterFiles.AndroidLibrary;
import edu.upb.lp.progra.adapterFiles.AppConnector;

public class MysticalDepthsConnector implements AppConnector {
    private Random rnd = new Random();
    private AndroidLibrary lib;
    private MysticalDepthsGame world = new MysticalDepthsGame(this);

    public MysticalDepthsConnector(AndroidLibrary lib) {
        this.lib = lib;
    }

    @Override
    public void onButtonPressed(String name) {
        if (name.equals("SIGUIENTE OLEADA")) {
            lib.removeAllButtons();
            lib.addButton("DISPARA!", 25, 65);
            lib.showTemporaryMessage("Oleada: " + world.getOleada());
            world.oleada();
        } else if (name.equals("DISPARA!")) {
            world.dispara();
        } else if (name.equals("Empezar de nuevo")) {
            lib.removeAllButtons();
            world = new MysticalDepthsGame(this);
            initialiseInterface();
        }
    }


    @Override
    public void onCellPressed(int vertical, int horizontal) {
        world.clic(vertical, horizontal);
    }

    @Override
    public void initialiseInterface() {
        //Main screen
        lib.reproduceSound("song");
        lib.configureScreen(7, 7, 0, 0, true, 0.1);
        rellenarDeMar();
        lib.setImageOnCell(6, 3, "guncrab");
        //Buttons
        lib.addButton("SIGUIENTE OLEADA", 20, 65);
        //Text
        lib.addTextField("OLEADA", "OLEADA: " + world.getOleada(), 30, 50);
    }

    private void rellenarDeMar() {
        String[] fondo = {"seaone", "seatwo", "seathree"};
        for (int fila = 0; fila < 7; fila++) {
            for (int columna = 0; columna < 7; columna++) {
                lib.setImageOnCell(fila, columna, fondo[rnd.nextInt(3)]);
            }
        }
    }

    public void borrarGunCrab(int v, int h) {
        lib.setImageOnCell(v, h, "seaone");
    }

    public void aparecerGunCrab(int v, int h) {
        lib.setImageOnCell(v, h, "guncrab");
    }

    public void desaparecerEnemigo(int v, int h) {
        lib.setImageOnCell(v, h, "seaone");
    }

    public void aparecerEnemigo(int v, int h) {
        lib.setImageOnCell(v, h, "enemy");
    }

    public void executeLater(Runnable r, int ms) {
        lib.executeLater(r, ms);
    }

    public void pantallaDeDerrota() {
        lib.stopSounds();
        lib.removeAllButtons();
        lib.configureScreen(1,1,0,0,true,0.1);
        lib.addButton("Empezar de nuevo" , 20 , 65);
        lib.setImageOnCell(0,0,"gameover");
    }

    public void aparecerMisil(int v, int h) {
        lib.setImageOnCell(v,h,"burbujas");
    }

    public void desaparecerMisil(int v, int h) {
        lib.setImageOnCell(v,h,"seaone");
    }

    public void prepararSiguienteOleada() {
        lib.removeAllButtons();
        lib.removeAllTextFields();
        lib.showTemporaryMessage("OLEADA SUPERADA!");
        lib.addTextField("OLEADA", "OLEADA: " + world.getOleada(), 30, 50);
        lib.addButton("SIGUIENTE OLEADA", 20, 65);
    }

    public void pantallaDeVictoria() {
        lib.removeAllButtons();
        lib.removeAllTextFields();
        lib.configureScreen(1, 1, 0, 0, true, 0.0);
        lib.setImageOnCell(0 , 0 , "victoria");
    }
}
