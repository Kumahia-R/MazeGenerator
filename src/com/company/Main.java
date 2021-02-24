package com.company;
import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) {
        Cell[][] grid = new Cell[5][11];
        int start = (int)(Math.random() * grid[0].length);
        for (int i = 0; i < grid[0].length; i++) {
            if (i != start) {
                System.out.print(" _");
            }
            else {
                System.out.print("  ");
            }
        }
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell();
                grid[i][j].printCell();
            }
            System.out.println("|");
        }
        Stack<Cell> backTrack = new Stack<>();

    }
}
class Cell {
    String vertWall = "|";
    String horizWall;
    boolean[] walls;
    boolean visited;

    // Initializing a boolean array, each boolean represents one side of the cell
    //false = no wall, true = wall
    //walls[0] = north wall, [1] = south, [2] = east, [3] = west
    Cell() {
        walls = new boolean[]{true, true, true, true};
        String vertWall = "|";
        horizWall = "_";
        visited = false;
    }

    public void printCell() {
        if (this.walls[1]) {
            System.out.print(vertWall);
        }
        if (this.walls[2]) {
            System.out.print(horizWall);
        }
    }
}