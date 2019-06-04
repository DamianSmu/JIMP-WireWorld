package test.Controller;

import Controller.FileIO;
import Model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class FileIOTest {

    @Test
    void readFromFile() {
        URL path = FileIOTest.class.getResource("test.txt");
        File file = new File(path.getFile());
        CellType[][] exp =
                {{GameOfLifeCellType.DEAD,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD},
                        {GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE},
                        {GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE}};
        IRuleSet ruleSet = new GameOfLifeRuleSet();

        try {
            CellType[][] cells = FileIO.readFromFile(file, ruleSet);
            for (int i = 0; i<cells.length;i++)
                for (int j=0; j<cells[0].length;j++)
                    assertEquals(exp[i][j], cells[i][j]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void saveToFile() {
        CellType[][] cells =
                {{GameOfLifeCellType.DEAD,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD},
                        {GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.DEAD,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE},
                        {GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE,GameOfLifeCellType.ALIVE}};
        File file = new File("test3.txt");
        IRuleSet ruleSet = new GameOfLifeRuleSet();
        try {
            file.createNewFile();
            URL path = FileIOTest.class.getResource("test2.txt");
            File file2 = new File(path.getFile());
            try {
                FileIO.saveToFile(file, cells, ruleSet);
                CellType[][] cells1 = FileIO.readFromFile(file2,ruleSet);
                CellType[][] cells2 = FileIO.readFromFile(file,ruleSet);
                for (int i = 0; i<cells2.length;i++)
                    for (int j=0; j<cells2[0].length;j++)
                        assertEquals(cells1[i][j], cells2[i][j]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}