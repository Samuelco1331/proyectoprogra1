package edu.upb.lp.progra.mysticalDepths;

public class Misil {
    private MysticalDepthsGame world;
    private int v = 5;
    private int h;
    private MovedorDeMisil movedor = new MovedorDeMisil(this);
    public Misil(MysticalDepthsGame world, int h) {
        this.h = h;
        this.world = world;
    }

    public int getV() {
        return v;
    }
    public int getH() {
        return h;
    }
    public void mover() {
        world.desaparecerMisil(this);
        if (v > 0) {
            v--;
            world.dibujarMisil(this);
        } else {
            detenerMovimiento();
            world.desaparecerMisil(this);
        }
    }
    public void executeLater(Runnable r, int ms) {
        world.executeLater(r, ms);
    }

    public void detenerMovimiento() {
        movedor.stop();
    }
    public void empezarMovimiento() {
        movedor.start();
    }

}
