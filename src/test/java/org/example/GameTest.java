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