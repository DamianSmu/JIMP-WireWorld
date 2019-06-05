package Model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WireWorldRuleSetTest {

    @Test
    void nextStep() {
        WireWorldRuleSet r = new WireWorldRuleSet();
        HashMap<CellType, Integer> neighbours = new HashMap<>();
        neighbours.put(WireWorldCellType.EMPTY, 6);
        neighbours.put(WireWorldCellType.CONDUCTOR, 1);
        neighbours.put(WireWorldCellType.HEAD, 1);
        neighbours.put(WireWorldCellType.TAIL, 0);
        assertEquals(WireWorldCellType.HEAD, r.nextStep(neighbours, WireWorldCellType.CONDUCTOR));
    }
}