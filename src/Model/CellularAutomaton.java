package Model;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CellularAutomaton
{
    private Cell[][] board;
    private int[][][][] close;
    private IRuleSet ruleSet;
    private int boardWidth;
    private int boardHeight;
    private int pxCellSize;
    private CellType defaultCellType;
    private CellType pickedColor;

    public CellularAutomaton(CellularAutomatonBuilder builder)
    {
        boardWidth = builder.boardWidth;
        boardHeight = builder.boardHeight;
        pxCellSize = builder.cellSize;
        ruleSet = builder.ruleSet;
        defaultCellType = builder.defaultCellType;

        initializeBoard();
    }

    private void initializeBoard()
    {
        board = new Cell[boardWidth][boardHeight];
        close = new int[boardWidth][boardHeight][][];
        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
                board[x][y] = new Cell(pxCellSize * x + x, pxCellSize * y + y, pxCellSize, GameOfLifeCellType.DEAD);
                close[x][y] = new int[][]{{x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}, {x - 1, y}, {x + 1, y}, {x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1}};
            }
        }
    }

    private void setNeighbours()
    {
        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
                HashMap<CellType, Integer> map = new HashMap<>();
                for (int i = 0; i < 8; i++)
                {
                    if (close[x][y][i][0] > 0 && close[x][y][i][0] < boardWidth && close[x][y][i][1] > 0 && close[x][y][i][1] < boardHeight)
                        map.merge(board[close[x][y][i][0]][close[x][y][i][1]].getType(), 1, Integer::sum);
                }
                board[x][y].setNeighbours(map);
            }
        }
    }

    public Rectangle[] getRectangles()
    {
        ArrayList<Rectangle> array = new ArrayList<>();
        forEachCell(cell -> array.add(cell.getRectangle()));
        return array.toArray(Rectangle[]::new);
    }

    public void nextStep()
    {
        setNeighbours();
        forEachCell(cell -> cell.nextStep(ruleSet));
    }

    public void draw()
    {
        forEachCell(Cell::draw);
    }

    public void setPickedType(CellType pickedType)
    {
        forEachCell(cell -> cell.setClickType(pickedType));
    }

    public void forEachCell(Consumer<Cell> action)
    {
        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
                action.accept(board[x][y]);
            }
        }
    }

    public static class CellularAutomatonBuilder
    {
        private int boardWidth;
        private int boardHeight;
        private int cellSize;
        private IRuleSet ruleSet;
        private CellType defaultCellType;

        public CellularAutomatonBuilder(int boardWidth, int boardHeight)
        {
            this.boardWidth = boardWidth;
            this.boardHeight = boardHeight;
        }

        public CellularAutomatonBuilder setRuleSet(IRuleSet ruleSet)
        {
            this.ruleSet = ruleSet;
            return this;
        }

        public CellularAutomatonBuilder setPxCellSize(int cellSize)
        {
            this.cellSize = cellSize;
            return this;
        }

        public CellularAutomatonBuilder setDefaultCellType(CellType defaultCellType)
        {
            this.defaultCellType = defaultCellType;
            return this;
        }

        public CellularAutomaton build()
        {
            return new CellularAutomaton(this);
        }
    }
}
