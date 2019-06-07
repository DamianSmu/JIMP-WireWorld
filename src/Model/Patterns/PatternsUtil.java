package Model.Patterns;

import Model.CellType;

public class PatternsUtil
{
    public static CellType[][] forName(String name)
    {
        switch (name)
        {
            case "Gun":
                return GameOfLifePatterns.gun;
            case "Forever":
                return GameOfLifePatterns.forever;
            case "Blinker":
                return GameOfLifePatterns.blinker;
            case "High Freq Gen":
                return WireWorldPatterns.highFreqGen;
            case "Low Freq Gen":
                return WireWorldPatterns.lowFreqGen;
            case "Diodes":
                return WireWorldPatterns.diodes;
            case "NAND":
                return WireWorldPatterns.NANDgate;
            case "NOT":
                return WireWorldPatterns.NOTgate;
            case "OR":
                return WireWorldPatterns.ORgate;
            case "XOR":
                return WireWorldPatterns.XORgate;
            default:
                return null;
        }
    }
}
