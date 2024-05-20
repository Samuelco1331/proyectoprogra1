package edu.upb.lp.progra.mysticalDepths;

import android.test.PerformanceTestCase;

public class MysticalDepthsGame {
    private MysticalDepthsConnector connector;
    private Enemy[] enemy;
    private Misil[] misiles = new Misil[20];
    private GunCrab gunCrab = new GunCrab(this);
    private int oleada = 1;
    private boolean perdio = false;
    public MysticalDepthsGame(MysticalDepthsConnector connector) {
        this.connector = connector;
    }

    public int getOleada() {
        return oleada;
    }

    public void clic(int vertical, int horizontal) {
        if (!perdio) {
            if (horizontal == 0) {
                gunCrab.izquierda();
            } else if (horizontal == 6) {
                gunCrab.derecha();
            }
        }
    }

    public void borrarGunCrab(int v, int h) {
        connector.borrarGunCrab(v, h);
    }

    public void aparecerGunCrab(int v, int h) {
        connector.aparecerGunCrab(v, h);
    }

    public void desaparecerEnemigo(int v, int h) {
        connector.desaparecerEnemigo(v, h);
    }

    public void aparecerEnemigo(Enemy enemy) {
        int v = enemy.getV();
        int h = enemy.getH();
        if (v == 6) {
            perdio = true;
            detenerMovimientoMisil();
            detenerMovimientoEnemigo();
            connector.pantallaDeDerrota();
        } else {
            boolean heEncontradoMisilQueMeMate = false;
            for (int i = 0; i < misiles.length; i++) {
                if (misiles[i] != null) {
                    if (v == misiles[i].getV()
                            && h == misiles[i].getH()) {
                        heEncontradoMisilQueMeMate = true;
                        destruirMisil(misiles[i]);
                        connector.desaparecerMisil(v,h);
                        connector.desaparecerEnemigo(v,h);
                        destruirEnemigo(enemy);
                    }
                }
            }
            if (!heEncontradoMisilQueMeMate && !perdio) {
                connector.aparecerEnemigo(v, h);
            }
        }
    }

    private void destruirEnemigo(Enemy enemy) {
        enemy.detenerMovimiento();
        for (int i = 0; i < this.enemy.length; i++) {
            if (enemy == this.enemy[i]) {
                this.enemy[i] = null;
            }
        }
        boolean quedanEnemigos = false;
        for (int i = 0; i < this.enemy.length; i++) {
            if (this.enemy[i] != null) {
                quedanEnemigos = true;
            }
        }
        if (!quedanEnemigos) {
            oleada ++;
            connector.prepararSiguienteOleada();
        }
    }


    private void destruirMisil(Misil misil) {
        misil.detenerMovimiento();
        for (int i = 0; i < enemy.length; i++) {
            if (misil == misiles[i]) {
                misiles[i] = null;
            }
        }
    }
    public void executeLater(Runnable r, int ms) {
        connector.executeLater(r, ms);
    }

    public void oleada() {
        if (oleada == 4) {
            perdio = true;
            connector.pantallaDeVictoria();
        } else {
            enemy = new Enemy[2 * oleada];
            for (int i = 0; i < enemy.length; i++) {
                enemy[i] = new Enemy(this);
                enemy[i].empezarMovimiento();
            }
        }
    }
    public void detenerMovimientoEnemigo() {
        for (int i = 0; i < enemy.length; i++) {
            if (enemy[i] != null) {
                enemy[i].detenerMovimiento();
            }
        }
    }
    public void detenerMovimientoMisil() {
        for (int i = 0; i < misiles.length; i++) {
            if (misiles[i] != null) {
                misiles[i].detenerMovimiento();
            }
        }
    }

    public void desaparecerMisil(Misil misil) {
        if (!perdio) {
            connector.desaparecerMisil(misil.getV(), misil.getH());
            for (int i = 0; i < misiles.length; i++) {
                if (misiles[i] == misil) {
                    misiles[i] = null;
                }
            }
        }
    }
    public void dibujarMisil(Misil misil) {
        int v = misil.getV();
        int h = misil.getH();
        if (v == 0) {
            connector.desaparecerMisil(v, h);
            detenerMovimientoMisil();
            destruirMisil(misil);
        } else {
            boolean heEncontradoEnemigoAlQueMatar = false;
            for (int i = 0; i < enemy.length; i++){
                if (enemy[i] != null) {
                    if (v == enemy[i].getV() && h == enemy[i].getH()) {
                        heEncontradoEnemigoAlQueMatar = true;
                        destruirEnemigo(enemy[i]);
                        destruirMisil(misil);
                        connector.desaparecerMisil(v, h);
                        connector.desaparecerEnemigo(v, h);
                    }
                }
            }
            if (!heEncontradoEnemigoAlQueMatar && !perdio) {
                connector.aparecerMisil(v,h);
            }
        }
    }
    public void crearMisil(int h) {
        Misil misil = new Misil(this, h);
        boolean heEncontrauLugarParaMiMisil = false;
        for (int i = 0; i < misiles.length && !heEncontrauLugarParaMiMisil; i++) {
            if (misiles[i] == null) {
                heEncontrauLugarParaMiMisil = true;
                misiles[i] = misil;
            }
        }
        if (heEncontrauLugarParaMiMisil) {
            connector.aparecerMisil(5, h);
            misil.empezarMovimiento();
        }
    }

    public void dispara() {
        gunCrab.dispara();
    }
}