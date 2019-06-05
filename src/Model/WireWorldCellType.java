package Model;

import javafx.scene.paint.Color;

public class WireWorldCellType extends CellType
{

    public static final WireWorldCellType EMPTY = new WireWorldCellType(Color.TRANSPARENT);
    public static final WireWorldCellType CONDUCTOR = new WireWorldCellType(Color.YELLOW);
    public static final WireWorldCellType HEAD = new WireWorldCellType(Color.BLUE);
    public static final WireWorldCellType TAIL = new WireWorldCellType(Color.RED);

    public WireWorldCellType(Color color)
    {
        super(color);
    }
}
