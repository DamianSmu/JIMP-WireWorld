package Model;

import java.util.HashMap;

public interface IRuleSet
{
      CellType nextStep(HashMap<CellType, Integer> neighbours, CellType cellType);
}
