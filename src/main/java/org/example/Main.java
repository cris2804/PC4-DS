package org.example;

import java.util.Scanner;

public class Main {
    static Map map = new Map();
    int playerHealth = new Player().getBaseHealth();
    static Tower tower = new Tower(10, 100, 10);
    static Game game = new Game();

    public static void main(String[] args) {
        System.out.println("Juego de torres iniciado");
        System.out.println("ingrese el tipo de torre(con el numero 3) y la posicion");
        Scanner sc = new Scanner(System.in);
        Scanner text = new Scanner(System.in);
        int tipo_torre = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        String comando = sc.nextLine();
        tower = new Tower(tipo_torre, x, y);
        map.printMap();

            System.out.println("primera oleada");
            System.out.println("nuevo mapa");
            game.startWave(); //iniciar la oleada
            map.placeTower(tower, x, y);
            map.printMap();




    }
}
