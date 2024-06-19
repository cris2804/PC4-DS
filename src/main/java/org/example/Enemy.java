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

