package edu.upb.lp.progra.newGame;

import java.util.Random;

import edu.upb.lp.progra.adapterFiles.AndroidLibrary;
import edu.upb.lp.progra.adapterFiles.AppConnector;

public class NewGameUI implements AppConnector {
    private AndroidLibrary gui;
    private Score score = new Score();

    public NewGameUI(AndroidLibrary gui) {
        this.gui = gui;
    }

    @Override
    public void onButtonPressed(String name) {
        if (name == "STORE") {
            gui.removeAllButtons();
            gui.removeAllTextFields();
            gui.configureScreen(2,2,0,0, true, 0.1);
            gui.addButton("BACK",20,50);
            rellenarDeMar(2,2);
            gui.setImageOnCell(0,0,"wall");
            // TODO insertar texto de precios en un text field
        }
        if (name == "BACK") {
            gui.removeAllButtons();
            // TODO inserte metodo que devuelva a la pantalla principal (con el progreso respectivo)
        }
        if (name == "SIGUIENTE OLEADA") {
            gui.configureScreen(6,6,0,0,true,0.0);
            rellenarDeMar(6,6);
        }
    }

    @Override
    public void onCellPressed(int vertical, int horizontal) {
        //EN LA TIENDA
        //  TODO al presionar una celda aparece un boton comprar
    }

    @Override
    public void initialiseInterface() {
        //Splash Screen
        gui.configureScreen(6,6,0,0,true,0.15);
        String[] fondo = {"seaone", "seatwo", "seathree"};
        Random rnd = new Random();
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0;    columna < 6; columna++) {
                gui.setImageOnCell(fila,columna,fondo[rnd.nextInt(3)]);
            }
        }
        //Buttons
        gui.addButton("SIGUIENTE OLEADA", 17,50);
        gui.addButton("STORE", 20,50);
        gui.addButton("INVENTORY", 20,50);
        //Text
        gui.addTextField("OLEADA", "OLEADA: " + score.getWave(), 20, 33);
        gui.addTextField("DINERO", "DINERO: " + score.getMoney(), 20, 33);
    }
    private void rellenarDeMar(int numberOfRows, int numberOfColumns) {
        Random rnd = new Random();
        String[] fondo = {"seaone", "seatwo", "seathree"};
        for (int fila = 0; fila < numberOfRows; fila++) {
            for (int columna = 0; columna < numberOfColumns; columna++) {
                gui.setImageOnCell(fila,columna,fondo[rnd.nextInt(3)]);
            }
        }
    }
}
