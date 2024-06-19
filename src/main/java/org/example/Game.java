package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Map map;
    private Player player;
    private List<Wave> waves;
    private int score;
    private final int baseHealth;

    public Game() { //definir constructores
        this.map = new Map();
        this.player = new Player();
        this.waves = new ArrayList<>();
        this.score = 0;
        this.baseHealth = 100;
        initializeGame();
    }

    private void initializeGame() { //inicializar el juego
        Map map = new Map();
        Player player = new Player();
        List<Wave> waves = new ArrayList<>();
        waves.add(new Wave(1));
        this.map = map;
        this.player = player;
        this.waves = waves;
    }

    public void placeTower(Tower tower, int x, int y) {//colocar la torre en la posicion(x,y)
        if (isValidTowerPlacement(x, y)) {
            System.out.println("entro");
            map.placeTower(tower, x, y);

        } else {
            System.out.println("no se puede colocar la torre en esa posicion");
        }
    }

    private boolean isValidTowerPlacement(int x, int y) { //validar la colocacion de la torre
        if (x>=0 && x < map.getMap().length && y>= 0 && y < map.getMap()[0].length && map.getMap()[x][y] == 0) {
            return true;
        }else{
            System.out.println("no se puede colocar la torre en esa posicion");
            return false;
        }

    }
    public void startWave() {
        Wave wave = new Wave(1);
        this.waves.add(wave);

    }

    public void updateGameState() {
//actualizar el estado del juego
        if (!waves.isEmpty()) {
            Wave wave = waves.get(0);
            if (wave.getEnemies().isEmpty()) {
                finishWave();
            }
        }
    }

    public void finishWave() { //finalizar la oleada
        if (!waves.isEmpty()) {
            waves.remove(0);
        }
    }

    public boolean isGameOver() {//validar si el juego ha terminado
        return baseHealth <= 0;
    }

}
