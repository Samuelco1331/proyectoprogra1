package edu.upb.lp.progra.newGame;

public class Wall {
    private int health = 50;
    public void getHitMarlin(Marlin marlin) {
        health -= marlin.hit();
    }
    public void getHitPiranha(Piranha piranha) {
        health -= piranha.hit();
    }
    public void getHitWhaleShark(WhaleShark whaleShark) {
        health -= whaleShark.hit();
    }
}
