package Model;

import java.util.HashMap;

public class WireWorldRuleSet implements IRuleSet
{
    @Override
    public CellType nextStep(HashMap<CellType, Integer> neighbours, CellType cellType)
    {
        int numberOfHeads = neighbours.getOrDefault(WireWorldCellType.HEAD, 0);

        if(cellType.equals(WireWorldCellType.CONDUCTOR) && (numberOfHeads >=1 && numberOfHeads <= 2))
            return WireWorldCellType.HEAD;

        if(cellType.equals(WireWorldCellType.HEAD))
            return WireWorldCellType.TAIL;

        if(cellType.equals(WireWorldCellType.TAIL))
            return WireWorldCellType.CONDUCTOR;

        return cellType;
    }
}
