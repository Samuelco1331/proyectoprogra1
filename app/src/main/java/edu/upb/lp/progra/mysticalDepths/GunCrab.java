package edu.upb.lp.progra.mysticalDepths;

public class GunCrab {
    private MysticalDepthsGame world;
    private int v = 6;
    private int h = 3;

    public GunCrab(MysticalDepthsGame world) {
        this.world = world;
    }

    public void izquierda() {
        if (h > 0) {
            //Desaparecer
            world.borrarGunCrab(v, h);
            //Modificar coordenadas
            h--;
            //Reaparecer
            world.aparecerGunCrab(v,h);
        }
    }
    public void derecha() {
        if (h < 6) {
            //desaparecer
            world.borrarGunCrab(v, h);
            //modificar coordenadas
            h++;
            //reaparecer
            world.aparecerGunCrab(v,h);
        }
    }

    public void dispara() {
        world.crearMisil(h);
    }
}
