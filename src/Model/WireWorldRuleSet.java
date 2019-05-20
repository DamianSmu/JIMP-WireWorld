package Model;

import java.util.HashMap;

public class WireWorldRuleSet implements IRuleSet
{
    @Override
    public CellType nextStep(HashMap<CellType, Integer> neighbours, CellType cellType)
    {
        int numberOfHeads = neighbours.getOrDefault(WireWorldCelltype.HEAD, 0);

        if(cellType.equals(WireWorldCelltype.CONDUCTOR) && (numberOfHeads >=1 && numberOfHeads <= 2))
            return WireWorldCelltype.HEAD;

        if(cellType.equals(WireWorldCelltype.HEAD))
            return WireWorldCelltype.TAIL;

        if(cellType.equals(WireWorldCelltype.TAIL))
            return WireWorldCelltype.CONDUCTOR;

        return cellType;
    }
}
