package edu.upb.lp.progra.mysticalDepths;

public class MovedorDeMisil implements Runnable {
    private Misil misil;
    private boolean running = false;

    public MovedorDeMisil(Misil misil) {
        this.misil = misil;
    }

    public void start() {
        running = true;
        misil.executeLater(this, 500);
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        if (running) {
            misil.mover();
            misil.executeLater(this, 500);
        }
    }
}
