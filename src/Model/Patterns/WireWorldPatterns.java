package Model.Patterns;

import Controller.FileIO;
import Model.CellType;
import Model.WireWorldRuleSet;

import java.io.File;
import java.io.IOException;

public class WireWorldPatterns
{
    public static CellType[][] diodes;
    public static CellType[][] highFreqGen;
    public static CellType[][] lowFreqGen;
    public static CellType[][] NANDgate;
    public static CellType[][] NOTgate;
    public static CellType[][] ORgate;
    public static CellType[][] XORgate;

    public static void init()
    {
        try
        {
            WireWorldRuleSet WWRuleset = new WireWorldRuleSet();
            diodes = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\diodes.txt"), WWRuleset);
            highFreqGen = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\highFrequencyGenerator.txt"), WWRuleset);
            lowFreqGen = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\lowFrequencyGenerator.txt"), WWRuleset);
            NANDgate = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\NANDgate.txt"), WWRuleset);
            NOTgate = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\NOTgate.txt"), WWRuleset);
            ORgate = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\ORgate.txt"), WWRuleset);
            XORgate = FileIO.readFromFile(new File("src\\Model\\Patterns\\PatternsResources\\XORgate.txt"), WWRuleset);
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
