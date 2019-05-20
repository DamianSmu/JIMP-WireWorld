package Model;

import javafx.scene.paint.Color;

public abstract class CellType
{
    private Color color;

    public CellType(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }
}
