package Controller;

import Model.*;

import java.io.*;
import java.util.Scanner;

public class FileIO {

    public static CellType[][] readFromFile(File file, IRuleSet ruleSet) throws FileNotFoundException,IllegalArgumentException {
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
        sc.close();
        return cells;
    }

    public static void saveToFile(File file, CellType[][] cells, IRuleSet ruleSet) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(String.valueOf(cells.length));
        writer.append(' ');
        writer.append(String.valueOf(cells[0].length));
        writer.newLine();
        if (ruleSet instanceof GameOfLifeRuleSet)
            GameOfLifeWriter(writer, file, cells);
        else if (ruleSet instanceof WireWorldRuleSet)
            WireWorldWriter(writer, file, cells);
        writer.close();
    }

    private static void GameOfLifeReader(Scanner sc, CellType[][] cells, int h, int w) throws IllegalArgumentException{
        int ln=0;
        int lw=0;
        while (sc.hasNext()) {
            char c = sc.next().charAt(0);
            if (c == '\n') {
                if (lw==w) {
                    ln++;
                    lw = 0;
                }
                else throw new IllegalArgumentException();
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
        if (ln<h-1)
            throw new IllegalArgumentException();
    }
    private static void WireWorldReader(Scanner sc, CellType[][] cells, int h, int w) throws IllegalArgumentException{
        int ln=0;
        int lw=0;
        while (sc.hasNext()) {
            char c = sc.next().charAt(0);
            if (c == '\n') {
                if (lw==w) {
                    ln++;
                    lw = 0;
                }
                else throw new IllegalArgumentException();
            }
            if (c == '0') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = WireWorldCellType.EMPTY;
                    lw++;
                }
                else throw new IllegalArgumentException(Character.toString(c));
            }
            if (c == '3') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = WireWorldCellType.CONDUCTOR;
                    lw++;
                }
                else throw new IllegalArgumentException();
            }
            if (c == '2') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = WireWorldCellType.HEAD;
                    lw++;
                }
                else throw new IllegalArgumentException();
            }
            if (c == '1') {
                if (ln<h && lw <w) {
                    cells[ln][lw] = WireWorldCellType.TAIL;
                    lw++;
                }
                else throw new IllegalArgumentException();
            }
        }
        if (ln<h-1)
            throw new IllegalArgumentException();
    }

    private static void GameOfLifeWriter(BufferedWriter writer, File file, CellType[][] cells) throws IOException {
        for (int i=0;i<cells.length;i++){
            for (int j=0;j<cells[0].length;j++){
                if (cells[i][j]==GameOfLifeCellType.DEAD)
                    writer.append('0');
                if (cells[i][j]==GameOfLifeCellType.ALIVE)
                    writer.append('1');
            }
            if (i!= cells.length-1)
                writer.newLine();
        }
    }

    private static void WireWorldWriter(BufferedWriter writer, File file, CellType[][] cells) throws IOException {
        for (int i=0;i<cells.length;i++){
            for (int j=0;j<cells[0].length;j++){
                if (cells[i][j]== WireWorldCellType.EMPTY)
                    writer.append('0');
                if (cells[i][j]== WireWorldCellType.CONDUCTOR)
                    writer.append('3');
                if (cells[i][j]== WireWorldCellType.HEAD)
                    writer.append('2');
                if (cells[i][j]== WireWorldCellType.TAIL)
                    writer.append('1');
            }
            if (i!= cells.length-1)
                writer.newLine();
        }
    }
}
