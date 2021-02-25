package com.company;
import java.util.*;
import java.io.*;
/* This program will generate a maze using a recursive backtracking algorithm*/
public class Main {
    String[] arrows = {"↑", "↓", "→", "←"}; //arrow keys for the upcoming solving algorithm(s)
    public static Stack<Cell> backTrack = new Stack<>();
    //main() function sets the size of the maze, calls the generator function and prints the maze in the Command line
    public static void main(String[] args) {
        int x = 20;
        int y = 20;
        Grid first = new Grid(x, y);
        backTrack.push(first.start);
        recurseBackTrack(first, backTrack.peek());
        first.printGrid();
    }
    // Function to generate the maze using recursive backtracker algorithm
    // Wikipedia's Maze generating algorithm page is a good read on how the algorithms work
    // Link: https://en.wikipedia.org/wiki/Maze_generation_algorithm
    public static void recurseBackTrack(Grid recursive, Cell start) {
        boolean end = false; //true when maze completes, condition for exiting the recursive function
        Cell newCell = new Cell();
        boolean choseNewCell = false;
        //my way of having the function randomly choose a direction to travel through cells
        //array of integers(0-3) that gets shuffled so the order will be random
        ArrayList<Integer> directions = new ArrayList<Integer>();
        directions.add(0);
        directions.add(1);
        directions.add(2);
        directions.add(3);
        Collections.shuffle(directions);
        start.visited = true; //marks that the Cell has been visited before
        if (backTrack.size() == 0) {
            end = true; // if the stack size is zero, every cell has been visited, meaning the maze is complete
        }
        /* The loop will search each bordering Cell and attempt to find a Cell that hasn't been visited yet
        If unvisited Cell is found, the current Cell will be pushed onto the stack and the recursion will have the
        unvisited Cell as the new argument.
        If no Cell was found, the function pops the previous Cell from the stack and uses it as an argument again
         */
        while (!choseNewCell && !end) {
            boolean atBound = false;
            if (directions.size() == 0) {
                recurseBackTrack(recursive, backTrack.pop());
                return;
            }
            switch (directions.get(0)) {
                case 0:
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
