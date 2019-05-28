package Controller;

import Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIO {

    public static CellType[][] readFromFile(String path, IRuleSet ruleSet) throws FileNotFoundException,IllegalArgumentException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        int h = sc.nextInt();
        int w = sc.nextInt();
        CellType[][] cells = new CellType[h][w];
        sc.useDelimiter("");
        while (sc.next().charAt(0) !='\n');
        if (ruleSet instanceof GameOfLifeRuleSet)
            GameOfLifeReader(sc, cells, h, w);
        else if (ruleSet instanceof WireWorldRuleSet)
            WireWorldReader(sc, cells, h, w);
        return cells;
    }

    private static void GameOfLifeReader(Scanner sc, CellType[][] cells, int h, int w) throws IllegalArgumentException{
        int ln=0;
        int lw=0;
        while (sc.hasNext()) {
            char c = sc.next().charAt(0);
            if (c == '\n') {
                ln++;
                lw = 0;
            }
            if (c == '0') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = GameOfLifeCellType.DEAD;
                    lw++;
                }
                else throw new IllegalArgumentException();
            }
            if (c == '1') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = GameOfLifeCellType.ALIVE;
                    lw++;
                }
                else throw new IllegalArgumentException();
            }
        }
    }
    private static void WireWorldReader(Scanner sc, CellType[][] cells, int h, int w) throws IllegalArgumentException{
        int ln=0;
        int lw=0;
        while (sc.hasNext()) {
            char c = sc.next().charAt(0);
            if (c == '\n') {
                ln++;
                lw = 0;
            }
            if (c == '0') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = WireWorldCelltype.EMPTY;
                    lw++;
                }
                else throw new IllegalArgumentException();
            }
            if (c == '1') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = WireWorldCelltype.CONDUCTOR;
                    lw++;
                }
                else throw new IllegalArgumentException();
            }
            if (c == '2') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = WireWorldCelltype.HEAD;
                    lw++;
                }
                else throw new IllegalArgumentException();
            }
            if (c == '3') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = WireWorldCelltype.TAIL;
                    lw++;
                }
                else throw new IllegalArgumentException();
            }
        }
    }
}
