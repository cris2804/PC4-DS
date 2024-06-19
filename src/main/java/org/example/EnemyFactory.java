package org.example;

public interface EnemyFactory {

    public Enemy createEnemy( int type, int health, int reward );
}
