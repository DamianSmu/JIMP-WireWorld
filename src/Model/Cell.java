package Model;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class Cell
{
    private Rectangle rectangle;
    private int pxPosX;
    private int pxPosY;
    private int pxCellSize;
    private CellType clickType;

    private HashMap<CellType, Integer> neighbours;
    private CellType type;

    public Cell(int pxPosX, int pxPosY, int pxCellSize, CellType type)
    {
        this.pxPosX = pxPosX;
        this.pxPosY = pxPosY;
        this.pxCellSize = pxCellSize;
        this.type = type;

        neighbours = new HashMap<>();
        rectangle = new Rectangle(pxPosX, pxPosY, pxCellSize - 1, pxCellSize - 1);
        rectangle.setFill(Color.TRANSPARENT);

        rectangle.setStrokeWidth(1);
        rectangle.setStroke(Color.TRANSPARENT);
    }

    public void draw()
    {
        rectangle.setFill(type.getColor());
    }

    public void nextStep(IRuleSet ruleSet)
    {
        setType(ruleSet.nextStep(neighbours, type));
    }

    public void setNeighbours(HashMap<CellType, Integer> neighbours)
    {
        this.neighbours = neighbours;
    }

    public Rectangle getRectangle()
    {
        return rectangle;
    }

    public CellType getType()
    {
        return type;
    }

    public void setType(CellType type)
    {
        this.type = type;
    }

    public void setClickType(CellType pickedType)
    {
        clickType = pickedType;
        rectangle.setOnMouseEntered(mouseEvent -> {

            if (mouseEvent.isAltDown())
            {
                setType(clickType);
                draw();
            }
        });
        rectangle.setOnMouseClicked(mouseEvent1 -> {
            if(mouseEvent1.getButton().equals(MouseButton.PRIMARY))
            {
                setType(clickType);
                draw();
            }
                }
        );
    }
}
