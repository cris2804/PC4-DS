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
