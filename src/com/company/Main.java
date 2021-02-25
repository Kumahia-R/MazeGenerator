package com.company;
import java.util.*;
import java.io.*;
public class Main {
    public static Stack<Cell> backTrack = new Stack<>();
    public static void main(String[] args) {
        int x = 10;
        int y = 20;
        Grid first = new Grid(x, y);
        backTrack.push(first.start);
        first.printGrid();
        recurseBackTrack(first, backTrack.peek());
    }
    public static void recurseBackTrack(Grid recursive, Cell start) {
        /*if (start.horizWall != " ") {
            start.horizWall = "x";
        }*/
        boolean end = false;
        recursive.printGrid();
        Cell newCell = new Cell();
        boolean choseNewCell = false;
        ArrayList<Integer> directions = new ArrayList<Integer>();
        directions.add(0);
        directions.add(1);
        directions.add(2);
        directions.add(3);
        Collections.shuffle(directions);
        start.visited = true;
        if (backTrack.size() == 0) {
            System.out.println("Maze complete");
            end = true;
        }
        while (!choseNewCell && !end) {
            boolean atBound = false;
            if (directions.size() == 0) {
                recurseBackTrack(recursive, backTrack.pop());
                return;
            }
            switch (directions.get(0)) {
                case 0:
                    System.out.println(directions.get(0));
                    if (start.rowPos > 0) {
                        newCell = recursive.gridSize[start.rowPos - 1][start.colPos];
                    } else {
                        atBound = true;
                    }
                    if (newCell.visited || atBound) {
                        directions.remove(0);
                    } else {
                        recursive.gridSize[start.rowPos - 1][start.colPos].horizWall = " ";
                        backTrack.push(newCell);
                        recurseBackTrack(recursive, newCell);
                        choseNewCell = true;
                    }
                    break;
                case 1:
                    System.out.println(directions.get(0));
                    if (start.rowPos < recursive.gridSize.length - 1) {
                        newCell = recursive.gridSize[start.rowPos + 1][start.colPos];
                    } else {
                        atBound = true;
                    }
                    if (newCell.visited || atBound) {
                        directions.remove(0);
                    } else {
                        start.horizWall = " ";
                        backTrack.push(newCell);
                        recurseBackTrack(recursive, newCell);
                        choseNewCell = true;
                    }
                    break;
                case 2:
                    System.out.println(directions.get(0));
                    if (start.colPos < recursive.gridSize[0].length - 1) {
                        newCell = recursive.gridSize[start.rowPos][start.colPos + 1];
                    } else {
                        atBound = true;
                    }
                    if (newCell.visited || atBound) {
                        directions.remove(0);
                    } else {
                        recursive.gridSize[start.rowPos][start.colPos + 1].vertWall = " ";
                        backTrack.push(newCell);
                        recurseBackTrack(recursive, newCell);
                        choseNewCell = true;
                    }
                    break;
                case 3:
                    System.out.println(directions.get(0));
                    if (start.colPos > 0) {
                        newCell = recursive.gridSize[start.rowPos][start.colPos - 1];
                    } else {
                        atBound = true;
                    }
                    if (newCell.visited || atBound) {
                        directions.remove(0);
                    } else {
                        start.vertWall = " ";
                        backTrack.push(newCell);
                        recurseBackTrack(recursive, newCell);
                        choseNewCell = true;
                    }
                    break;
            }
        }
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
        walls = new boolean[]{true, true};
        String vertWall = "|";
        horizWall = "_";
        visited = false;
    }

    public void printCell() {
        if (this.walls[0]) {
            System.out.print(vertWall);
        }
        else {
            System.out.print(" ");
        }
        if (this.walls[1]) {
            System.out.print(horizWall);
        }
        else {
            System.out.print(" ");
        }
    }
}
class Grid {
    Cell[][] gridSize;
    Cell start;
    Cell end;
    int startIndex;
    int endIndex;

    Grid(int rows,int cols) {
        gridSize = new Cell[rows][cols];
        start = new Cell();
        end = new Cell();
        startIndex = (int)(Math.random() * cols);
        endIndex = (int)(Math.random() * cols);
        for (int i = 0; i < gridSize.length; i++) {
            for (int j = 0; j < gridSize[i].length; j++) {
                if (i == 0 && j == startIndex) {
                    gridSize[i][j] = start;
                }
                else if (i == gridSize.length - 1 && j == endIndex) {
                    gridSize[i][j] = end;
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
        end.walls[1] = false;
        for (Cell i : gridSize[0]) {
            if (i != start) {
                System.out.print(" _");
            }
            else {
                System.out.print("  ");
            }
        }
        System.out.println();
        for (Cell[] i : gridSize) {
            for (Cell j : i ) {
                j.printCell();
            }
            System.out.println("|");
        }
    }
}