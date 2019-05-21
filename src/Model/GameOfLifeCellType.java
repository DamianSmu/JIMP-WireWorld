package Model;

import javafx.scene.paint.Color;

public class GameOfLifeCellType extends CellType
{
    public static final GameOfLifeCellType DEAD = new GameOfLifeCellType(Color.TRANSPARENT);
    public static final GameOfLifeCellType ALIVE = new GameOfLifeCellType(Color.WHITE);

    public GameOfLifeCellType(Color color)
    {
        super(color);
    }
}
