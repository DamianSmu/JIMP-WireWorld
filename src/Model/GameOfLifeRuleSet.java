package Model;

import java.util.HashMap;

public class GameOfLifeRuleSet implements IRuleSet
{
    @Override
    public CellType nextStep(HashMap<CellType, Integer> neighbours, CellType cellType)
    {
        int numberOfAlives = neighbours.getOrDefault(GameOfLifeCellType.ALIVE, 0);
        if (numberOfAlives == 3 && cellType.equals(GameOfLifeCellType.DEAD))
            return GameOfLifeCellType.ALIVE;

        if ((numberOfAlives < 2 || numberOfAlives > 3) && cellType.equals(GameOfLifeCellType.ALIVE))
            return GameOfLifeCellType.DEAD;

        return cellType;
    }
}
