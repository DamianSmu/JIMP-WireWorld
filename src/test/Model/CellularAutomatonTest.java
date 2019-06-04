package test.Model;

import Model.CellType;
import Model.CellularAutomaton;
import Model.GameOfLifeCellType;
import Model.GameOfLifeRuleSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellularAutomatonTest {

    @Test
    void nextStep() {
        CellularAutomaton c = new CellularAutomaton.CellularAutomatonBuilder(5, 3)
                .setDefaultCellType(GameOfLifeCellType.DEAD)
                .setRuleSet(new GameOfLifeRuleSet())
                .setPxCellSize(10)
                .build();
        CellType[][] cells =
                {{GameOfLifeCellType.DEAD,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD},
                 {GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE},
                 {GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE}};
        c.changeBoard(cells);
        c.nextStep();
        CellType[][] exp =
                {{GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD},
                 {GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.ALIVE},
                 {GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.ALIVE,GameOfLifeCellType.DEAD,GameOfLifeCellType.ALIVE}};
        for (int i=0;i<c.getBoard().length;i++)
            for (int j=0;j<c.getBoard()[0].length;j++)
                assertEquals(exp[j][i],c.getBoard()[i][j].getType());
    }
}