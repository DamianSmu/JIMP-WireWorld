package test.Model;

import Model.CellType;
import Model.WireWorldCelltype;
import Model.WireWorldRuleSet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WireWorldRuleSetTest {

    @Test
    void nextStep() {
        WireWorldRuleSet r = new WireWorldRuleSet();
        HashMap<CellType, Integer> neighbours = new HashMap<>();
        neighbours.put(WireWorldCelltype.EMPTY, 6);
        neighbours.put(WireWorldCelltype.CONDUCTOR, 1);
        neighbours.put(WireWorldCelltype.HEAD, 1);
        neighbours.put(WireWorldCelltype.TAIL, 0);
        assertEquals(WireWorldCelltype.HEAD, r.nextStep(neighbours, WireWorldCelltype.CONDUCTOR));
    }
}