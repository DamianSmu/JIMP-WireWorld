package Model;

import javafx.scene.paint.Color;

public class WireWorldCelltype extends CellType
{

    public static final WireWorldCelltype EMPTY = new WireWorldCelltype(Color.TRANSPARENT);
    public static final WireWorldCelltype CONDUCTOR = new WireWorldCelltype(Color.YELLOW);
    public static final WireWorldCelltype HEAD = new WireWorldCelltype(Color.BLUE);
    public static final WireWorldCelltype TAIL = new WireWorldCelltype(Color.RED);

    public WireWorldCelltype(Color color)
    {
        super(color);
    }
}
