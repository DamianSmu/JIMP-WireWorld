package Model.Patterns;

import Model.CellType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

public class Pattern
{
    private CellType[][] cells;
    private Rectangle[][] rectangles;
    private double pxCellSize;

    public Pattern(CellType[][] cells, double pxPosX, double pxPosY, double pxCellSize)
    {
        this.pxCellSize = pxCellSize;
        this.cells = cells;
        rectangles = new Rectangle[cells.length][cells[0].length];
        for (int i = 0; i < cells[0].length; i++)
        {
            for (int j = 0; j < cells.length; j++)
            {
                rectangles[j][i] = new Rectangle(pxPosX + i * pxCellSize, pxPosY + j * pxCellSize, pxCellSize - 1, pxCellSize - 1);
                rectangles[j][i].setFill(cells[j][i].getColor());
                rectangles[j][i].setStrokeWidth(1);
                rectangles[j][i].setStroke(Color.TRANSPARENT);
            }
        }
    }

    public void moveTo(double pxPosX, double pxPosY)
    {
        for (int i = 0; i < cells[0].length; i++)
        {
            for (int j = 0; j < cells.length; j++)
            {
                rectangles[j][i].setX(pxPosX + i * pxCellSize);
                rectangles[j][i].setY(pxPosY + j * pxCellSize);
            }
        }
    }

    public Rectangle[] getRectangles()
    {
        ArrayList<Rectangle> toReturn = new ArrayList<>();
        for (Rectangle[] rects : rectangles)
        {
            toReturn.addAll(Arrays.asList(rects));
        }
        return toReturn.toArray(Rectangle[]::new);
    }

    public CellType[][] getCells()
    {
        return cells;
    }
}
