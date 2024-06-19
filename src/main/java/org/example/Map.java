package org.example;

public class Map {
    int[][] map = {
            {0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {1, 0, 0, 1, 2},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0}};
    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public void printMap() { //imprimir el mapa
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void placeTower(Tower tower, int x, int y) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i==x && j==y) {
                    map[i][j] = 3;
                    System.out.println("se actualiza");
                }
            }
        }
    }
}
