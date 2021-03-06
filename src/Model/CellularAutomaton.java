package Model;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class CellularAutomaton
{
    private Cell[][] board;
    private int[][][][] close;
    private IRuleSet ruleSet;
    private int boardWidth;
    private int boardHeight;
    private int pxCellSize;
    private CellType defaultCellType;
    private CellType pickedType;

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
                board[x][y] = new Cell(pxCellSize * x, pxCellSize * y, pxCellSize, defaultCellType);
                close[x][y] = new int[][]{{x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}, {x - 1, y}, {x + 1, y}, {x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1}};
            }
        }
        setPickedType(defaultCellType);
    }

    private synchronized void setNeighbours()
    {
        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
                HashMap<CellType, Integer> map = board[x][y].getNeighboursMap();
                map.clear();
                for (int i = 0; i < 8; i++)
                {
                    if (close[x][y][i][0] > 0 && close[x][y][i][0] < boardWidth && close[x][y][i][1] > 0 && close[x][y][i][1] < boardHeight)
                        map.merge(board[close[x][y][i][0]][close[x][y][i][1]].getType(), 1, Integer::sum);
                }
                board[x][y].setNeighbours(map);
            }
        }
    }

    public void changeBoard(CellType[][] cells)
    {
        boardHeight = cells.length;
        boardWidth = cells[0].length;
        initializeBoard();
        for (int i = 0; i < boardWidth; i++)
        {
            for (int j = 0; j < boardHeight; j++)
            {
                board[i][j].setType(cells[j][i]);
            }
        }
        setNeighbours();
    }

    public synchronized void addCellsToBoard(CellType[][] cells, double pxPosX, double pxPosY)
    {
        int idX = (int) ((pxPosX - pxPosX % pxCellSize) / pxCellSize);
        int idY = (int) ((pxPosY - pxPosY % pxCellSize) / pxCellSize);

        int height = cells.length;
        int width = cells[0].length;

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                board[j + idX][i + idY].setType(cells[i][j]);
                draw();
            }
        }
        setNeighbours();
    }

    public synchronized void resizeBoard (int width, int height){
        CellType[][] cells = new CellType[height][width];
        for (int i = 0; i<height;i++)
            for (int j= 0; j<width;j++) {
                if (j<boardWidth && i<boardHeight)
                    cells[i][j] = board[j][i].getType();
                else
                    cells[i][j] = defaultCellType;
            }
        boardWidth = width;
        boardHeight = height;
        initializeBoard();
        addCellsToBoard(cells, 0,0);
    }

    public Rectangle[] getRectangles()
    {
        ArrayList<Rectangle> array = new ArrayList<>();
        forEachCell(cell -> array.add(cell.getRectangle()));
        return array.toArray(Rectangle[]::new);
    }

    public synchronized void nextStep()
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
        this.pickedType = pickedType;
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

    public IRuleSet getRuleSet()
    {
        return ruleSet;
    }

    public Cell[][] getBoard()
    {
        return board;
    }

    public void zoom(int pxCellSize)
    {
        this.pxCellSize = pxCellSize;
        CellType[][] oldCellTypes = getCellTypes();
        changeBoard(oldCellTypes);
        forEachCell(Cell::draw);
        //forEachCell(cell -> cell.setClickType(pickedType));
    }

    public CellType[][] getCellTypes()
    {
        CellType[][] ret = new CellType[boardHeight][boardWidth];
        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
                ret[y][x] = board[x][y].getType();
            }
        }
        return ret;
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
