package com.company;

class Cell {
    String vertWall = "|";
    String horizWall;
    String blankHorizWall;
    boolean[] walls;
    boolean visited;
    int rowPos;
    int colPos;

    // Initializing a boolean array, each boolean represents one side of the cell
    //false = no wall, true = wall
    //walls[0] = left (vertical) wall, [1] = bottom (horizontal) wall
    Cell() {
        walls = new boolean[]{true, true};
        vertWall = "|";
        blankHorizWall = " ";
        horizWall = "_";
        visited = false;
    }

    public void printCell() {
        if (this.walls[0]) {
            vertWall = "|";
            System.out.print(vertWall + " ");
        }
        else {
            vertWall = " ";
            System.out.print(vertWall + vertWall);
        }
        if (this.walls[1]) {
            System.out.print(horizWall + horizWall);
        }
        else {
            System.out.print(blankHorizWall + blankHorizWall);
        }
    }
}
//Grid acts as a 2D array of Cells
//Contains special "start" Cell and "end" Cell that will display the entrance and exit of the maze
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
                System.out.print("  __");
            }
            else {
                System.out.print("   ");
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