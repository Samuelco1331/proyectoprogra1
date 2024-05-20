package edu.upb.lp.progra.mysticalDepths;

public class MovedorDeEnemigo implements Runnable {
    private Enemy enemy;
    private boolean running = false;

    public MovedorDeEnemigo(Enemy enemy) {
        this.enemy = enemy;
    }



    public void start() {
        running = true;
        enemy.executeLater(this, 1500);
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        if (running) {
            enemy.mover();
            enemy.executeLater(this, 4000);
        }
    }
}
