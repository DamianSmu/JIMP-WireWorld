package Model.Patterns;

import Controller.FileIO;
import Model.CellType;
import Model.GameOfLifeRuleSet;

import java.io.File;
import java.io.IOException;

public class GameOfLifePatterns
{
    public static CellType[][] gun;
    public static CellType[][] blinker;
    public static CellType[][] forever;

    public static void init()
    {
        try
        {
            GameOfLifeRuleSet GOFRuleSet = new GameOfLifeRuleSet();
            gun = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\gun.txt"), GOFRuleSet);
            blinker = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\blinker.txt"), GOFRuleSet);
            forever = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\forever.txt"), GOFRuleSet);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
