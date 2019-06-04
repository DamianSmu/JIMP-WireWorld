package test.Model;

import Model.CellType;
import Model.GameOfLifeCellType;
import Model.GameOfLifeRuleSet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeRuleSetTest {

    @Test
    void nextStep() {
        GameOfLifeRuleSet r = new GameOfLifeRuleSet();
        HashMap<CellType, Integer> neighbours = new HashMap<>();
        neighbours.put(GameOfLifeCellType.ALIVE, 3);
        neighbours.put(GameOfLifeCellType.DEAD, 5);
        assertEquals(GameOfLifeCellType.ALIVE, r.nextStep(neighbours, GameOfLifeCellType.DEAD));
    }
}