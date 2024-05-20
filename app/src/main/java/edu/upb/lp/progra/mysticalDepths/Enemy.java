package edu.upb.lp.progra.mysticalDepths;

import java.util.Random;

public class Enemy {
    private Random rnd = new Random();
    private MysticalDepthsGame world;
    private int v = 0;
    private int h = rnd.nextInt(6);
    private MovedorDeEnemigo movedor = new MovedorDeEnemigo(this);

    public Enemy(MysticalDepthsGame world) {
        this.world = world;
    }
    public int getV() {
        return v;
    }
    public int getH() {
        return h;
    }
    public void mover() {
        //Desaparece
        world.desaparecerEnemigo(v, h);
        //Modifica Coordenadas
        v++;
        if (h == 0) {
            h = 1;
        } else if (h == 6) {
            h = 5;
        } else {
            h += rnd.nextInt(3) - 1;
        }
        //Aparecer
        world.aparecerEnemigo(this);
    }
    public void executeLater(Runnable r, int ms) {
        world.executeLater(r, ms);
    }

    public void empezarMovimiento() {
        movedor.start();
    }

    public void detenerMovimiento() {
            movedor.stop();
    }
}
