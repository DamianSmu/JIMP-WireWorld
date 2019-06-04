package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

public class Pattern
{
    private CellType[][] cells;
    private Rectangle[][] rectangles;
    private double pxCellSize;

    public Pattern(/*Celltype[][] cells*/double pxPosX, double pxPosY, double pxCellSize)
    {
        this.pxCellSize = pxCellSize;
        cells = new CellType[][] {{GameOfLifeCellType.ALIVE, GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE}};
        rectangles = new Rectangle[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++)
        {
            for (int j = 0; j < cells[0].length; j++)
            {
                rectangles[i][j] = new Rectangle(pxPosX + i * pxCellSize, pxPosY + j * pxCellSize, pxCellSize - 1, pxCellSize - 1);
                rectangles[i][j].setFill(cells[i][j].getColor());
                rectangles[i][j].setStrokeWidth(1);
                rectangles[i][j].setStroke(Color.TRANSPARENT);
            }
        }
    }

    public void moveTo(double pxPosX, double pxPosY)
    {
        for (int i = 0; i < cells.length; i++)
        {
            for (int j = 0; j < cells[0].length; j++)
            {
                rectangles[i][j].setX(pxPosX + i * pxCellSize);
                rectangles[i][j].setY(pxPosY + j * pxCellSize);
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
