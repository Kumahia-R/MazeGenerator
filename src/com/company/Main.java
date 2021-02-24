package com.company;
import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) {
        Cell perfect = new Cell();
        Cell[] grid = new Cell[10];
        grid[0].printCell();
    }
}
class Cell {
    String vertWall = "|";
    String horizWall = "_";

    // Initializing a boolean array, each boolean represents one side of the cell
    //false = no wall, true = wall
    Cell() {
        System.out.println("Construct");
        boolean[] walls = {false, false, false, false};
        String vertWall = "|";
        String horizWall = "_";
    }

    public void printCell() {
        System.out.print(vertWall + horizWall + vertWall);
    }
}