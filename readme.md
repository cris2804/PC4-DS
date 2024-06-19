# PC4RUIZ ARICA

- parte1
    
    # Tower Defense
    
    El juego es un juego de defensa de torres donde el jugador debe defender su base de oleadas de enemigos colocando torres en lugares estratégicos del mapa
    
    **Matriz del mapa:**
    
    - Una celda vacía (' ' o 0) representa un espacio disponible para colocar una torre.
    - Una celda de camino ('C' o 1) representa el camino por el que los enemigos se desplazan.
    - La base ('B' o 2) representa el objetivo que los enemigos deben alcanzar.
    - En este caso use {0 ,1,2} como **elementos del mapa para los enemigos**
    - {3} es la instancia de una **torre**
    
    Los enemigos seguirán el camino definido por las '1' hasta llegar a la base '2'.
    
    ### Map
    
    ```java
    package org.example;
    
    public class Map {
    //creacion del mapa (diseño predefinido)
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
    
        public void printMap() {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }
    
    }
    
    ```
    
    ## Piezas del juego
    
    ### **Enemigos**
    
    Los enemigos son las entidades que el jugador debe detener. Tienen varias características que determinan su comportamiento y dificultad.
    
    ```java
    package org.example;
    
    public class Enemy {
        private int speed; // número de celdas por turno
        private int health;//vida
        private int reward;//premio
    
        public Enemy(int speed, int health, int reward) {
            this.speed = speed;
            this.health = health;
            this.reward = reward;
        }
        // Constructores, getters y setters
    
        public int getSpeed() {
            return speed;
        }
    }
    
    ```
    
    ## Tipos de enemigos
    
    ### BasicEnemy (enemigo basico)
    
    ```java
    package org.example;
    
    public class BasicEnemy extends Enemy {
        public BasicEnemy() {
            super(1, 100, 10); // velocidad, vida, recompensa
        }
    }
    
    ```
    
    ### BossEnemy (jefe enemigo)
    
    ```java
    package org.example;
    
    public class BossEnemy extends Enemy {
        public BossEnemy() {
            super(3, 200, 20); // velocidad, vida, recompensa
        }
    
    }
    
    ```
    
    ### **Torres**
    
    Descripción: Las torres son las defensas que el jugador coloca en el mapa para detener a los
    enemigos. Tienen diferentes características y habilidades.
    **Tipos de torres:**
    Cañón: Alto daño, corto alcance.
    Láser: Daño moderado, largo alcance.
    Flecha: Daño bajo, alcance moderado, alta velocidad de disparo
    
    ```java
    package org.example;
    
    public class Tower {
        private int damage;
        private int range;
        private int fireRate; // turnos entre disparos
        // Constructores, getters y setters
    
        public Tower(int damage, int range, int fireRate) {
            this.damage = damage;
            this.range = range;
            this.fireRate = fireRate;
        }
    
        public int getDamage() {
            return damage;
        }
        public int getRange() {
            return range;
        }
        public int getFireRate() {
            return fireRate;
        }
    }
    
    ```
    
    ### Sistema de oleadas
    
    Descripción: Los enemigos llegan en oleadas que aumentan en dificultad a medida que avanza el
    juego. Cada oleada puede tener más enemigos o enemigos más fuertes.
    Configuración de oleadas:
    Número de enemigos: Cantidad de enemigos por oleada.
    Tipo de enemigos: Variedad de enemigos que aparecen en la oleada.
    
    ```java
    package org.example;
    
    import java.util.ArrayList;
    import java.util.List;
    
    public class Wave {
        private List<Enemy> enemies;
        private int waveNumber;
        public Wave(int waveNumber) {
            this.waveNumber = waveNumber;
            this.enemies = generateEnemies(waveNumber);
        }
        private List<Enemy> generateEnemies(int waveNumber) {
            List<Enemy> enemies = new ArrayList<>();
            for (int i = 0; i < waveNumber * 5; i++) { // más enemigos cada oleada
                enemies.add(new BasicEnemy());
            }
            if (waveNumber % 5 == 0) { // jefe cada 5 oleadas
                enemies.add(new BossEnemy());
            }
            return enemies;
        }
        // Métodos para manejar el progreso de la oleada
        public int getWaveNumber() {
            return waveNumber;
        }
        public List<Enemy> getEnemies() {
            return enemies;
        }
    }
    ```
    
    ### Sistema de puntuación
    
    Descripción: El jugador gana puntos por derrotar enemigos y pierde puntos si los enemigos alcanzan
    la base.
    Puntos por enemigo derrotado: Cada enemigo tiene una recompensa que se suma a la puntuación
    del jugador.
    
    ```java
    package org.example;
    
    public class Player {
        private int score;
        private int baseHealth;
        public Player() {
            this.score = 0;
            this.baseHealth = 100;
        }
        public void addScore(int points) {
            this.score += points;
        }
        public void deductBaseHealth(int damage) {
            this.baseHealth -= damage;
        }
        public int getScore() {
            return score;
        }
        public int getBaseHealth() {
            return baseHealth;
        }
        // Métodos adicionales según las necesidades del juego
    }
    
    ```
    
    ## Logica del Juego
    
    ### Game
    
    En la clase `Game` ira la logica del juego , donde se añadirán los métodos para insertar la torres en cierta posición , inicializar las oleada y actualizar el estado del juego.
    
    ```java
    package org.example;
    
    import java.util.ArrayList;
    import java.util.List;
    
    public class Game {
        private Map map;
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
                map.placeTower(tower, x, y);
    
            } else {
                System.out.println("no se puede colocar la torre en esa posicion");
            }
        }
    
        private boolean isValidTowerPlacement(int x, int y) { //validar la colocacion de la torre
            if (x >= 0 && x < map.getMap().length && y >= 0 && y < map.getMap()[0].length && map.getMap()[x][y] == 0) {
                return true;
            }
            return false;
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
    
    ```
    

Una vez hecha la logica del juego procedemos a iniciar el juego en la `Main` : 

```java
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
            game.placeTower(tower, x, y);
            map.printMap();
        

    }
}

```

Salida : 

![Untitled](PC4RUIZ%20ARICA%20338e8c32d9b44557a86f6a5f4a0b3547/Untitled.png)

## Preguntas de diseño e implementación

**Diseño de la clase Map:**

¿Cómo implementarías la clase Map para representar el mapa del juego, asegurando que se puedan agregar y verificar posiciones de torres y caminos?

Para ello se crea dos metoods un metodo donde se pueda insertar las torres ,talque su posicion sea valida este metodo es : 
`placeTower`  y para validar el metodo 
`isValidTowerPlacement`
• Implementa un método en la clase Map llamado isValidPosition(int x, int y) que verifique si una posición es válida para colocar una torre.

```java
 private boolean isValidTowerPlacement(int x, int y) { //validar la colocacion de la torre
        if (x>=0 && x < map.getMap().length && y>= 0 && y < map.getMap()[0].length && map.getMap()[x][y] == 0) {
            return true;
        }else{
            System.out.println("no se puede colocar la torre en esa posicion");
            return false;
        }

    }
```

**Enemigos con diferentes características:**

Diseña e implementa una clase SpeedyEnemy que herede de Enemy y tenga una velocidad mayor pero menos vida.

La clase SpeedyEnemy se muestra acontinuacion : 

```java
package org.example;

public class SpeedyEnemy extends Enemy{

    public SpeedyEnemy() {
        super(3, 30, 6);
    }
}

```

• ¿Cómo gestionarías el movimiento de los enemigos en el mapa, asegurando que sigan el camino predefinido?

Creando un metodo `moveEnemy`  donde se pueda verficar el movimiento de ellos y validarlo creando la respectivas pruebas unitarias

## Pruebas unitarias

Creamos las pruebas unitaria `GameTest`para la clase Game , empezaremos con el metodo `placeTower` : 

```java
package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void placeTower() {
        // Arrange
        Game game = new Game();
        Tower tower = new Tower(1, 1, 1);
        int x = 2;
        int y = 2;

        // Act
        game.placeTower(tower, x, y);

        // Assert
        assertEquals(3, game.map.getMap()[x][y]);
    }
}
```

ejecutamos la prueba : 

![Untitled](images/Untitled%201.png)