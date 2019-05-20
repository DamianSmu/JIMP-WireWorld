package Model;

public class Automatos
{
    private static final int BOARD_WIDTH = 100;
    private static final int BOARD_HEIGHT = 100;
    private static final int CELL_SIZE = 10;

    public static CellularAutomaton createGameOfLifeAutomaton()
    {
        return new CellularAutomaton.CellularAutomatonBuilder(BOARD_WIDTH, BOARD_HEIGHT)
            .setDefaultCellType(GameOfLifeCellType.DEAD)
            .setRuleSet(new GameOfLifeRuleSet())
            .setPxCellSize(CELL_SIZE)
            .build();
    }

    public static CellularAutomaton createWireWorldAutomaton()
    {
        return new CellularAutomaton.CellularAutomatonBuilder(BOARD_WIDTH, BOARD_HEIGHT)
                .setDefaultCellType(GameOfLifeCellType.DEAD)
                .setRuleSet(new WireWorldRuleSet())
                .setPxCellSize(CELL_SIZE)
                .build();
    }
}
