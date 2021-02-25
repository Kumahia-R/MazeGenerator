package com.company;
import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) {
        int x = 5;
        int y = 11;
        Grid first = new Grid(x, y);
        first.printGrid();
        recurseBackTrack(first, first.start);
    }
    public static void recurseBackTrack(Grid recursive, Cell start) {
        Stack<Cell> backTrack = new Stack<>();
        Cell newCell = new Cell();
        boolean choseNewCell = false;
        ArrayList<Integer> directions = new ArrayList<Integer>();
        directions.add(0);
        directions.add(1);
        directions.add(2);
        directions.add(3);
        Collections.shuffle(directions);
        backTrack.push(start);
        start.visited = true;
        while (!choseNewCell) {
            switch (directions.get(0)) {
                case 0:
                    newCell = recursive.gridSize[start.rowPos - 1][start.colPos];
                    if (newCell.visited) {
                        directions.remove(0);
                    }
                    else {
                        choseNewCell = true;
                    }
                    break;
                case 1:
                    newCell = recursive.gridSize[start.rowPos + 1][start.colPos];
                    if (newCell.visited) {
                        directions.remove(0);
                    }
                    else {
                        choseNewCell = true;
                    }
                    break;
                case 2:
                    newCell = recursive.gridSize[start.rowPos][start.colPos + 1];
                    if (newCell.visited) {
                        directions.remove(0);
                    }
                    else {
                        choseNewCell = true;
                    }
                    break;
                case 3:
                    newCell = recursive.gridSize[start.rowPos][start.colPos - 1];
                    if (newCell.visited) {
                        directions.remove(0);
                    }
                    else {
                        choseNewCell = true;
                    }
                    break;
                default:
                    backTrack.pop();
                    recurseBackTrack(recursive, backTrack.peek());
            }
        }
        recurseBackTrack(recursive, newCell);
    }
}
class Cell {
    String vertWall = "|";
    String horizWall;
    boolean[] walls;
    boolean visited;
    int rowPos;
    int colPos;

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
class Grid {
    Cell[][] gridSize;
    Cell start;
    int startIndex;

    Grid(int rows,int cols) {
        gridSize = new Cell[rows][cols];
        start = new Cell();
        startIndex = (int)(Math.random() * cols);
        for (int i = 0; i < gridSize.length; i++) {
            for (int j = 0; j < gridSize[i].length; j++) {
                if (i == 0 && j == startIndex) {
                    gridSize[i][j] = start;
                }
                else {
                    gridSize[i][j] = new Cell();
                }
                gridSize[i][j].rowPos = i;
                gridSize[i][j].colPos = j;
            }
        }
    }
    public void printGrid() {
        for (Cell i : gridSize[0]) {
            if (i != start) {
                System.out.print(" _");
            }
            else {
                System.out.print("  ");
            }
        }
        System.out.println();
        for (int i = 0; i < gridSize.length; i++) {
            for (int j = 0; j < gridSize[i].length; j++ ) {
                gridSize[i][j].printCell();
            }
            System.out.println("|");
        }
    }
}