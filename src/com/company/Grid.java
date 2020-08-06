package com.company;

import java.io.IOException;
import java.util.Scanner;

// Grid
public class Grid {
    private static Scanner scanner = new Scanner(System.in);
    private int width = 4;
    private int height = 4;
    private int targetCellX;
    private int targetCellY;
    private int[] tempArr;
    private Cell[][] grid;

    Grid(Cell[][] grid, int targetCellX, int targetCellY){
        this.grid = grid;
        this.targetCellX = targetCellX;
        this.targetCellY = targetCellY;
    }

    // Draw the grid
    public void drawGrid() {
        for(int row = 0; row < grid.length; row++ ){
            for(int col = 0; col < grid[row].length; col++ ){
                if (grid[row][col].getNum() >= 10 && grid[row][col].getNum() <= 99) {
                    System.out.print("(" + grid[row][col].getNum() + ")");
                } else {
                    if (grid[row][col].getNum() == 0) {
                        System.out.print("(  )");
                    } else {
                        System.out.print("( " + grid[row][col].getNum() + ")");
                    }
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    // Compute the next generation
    public void nextGeneration() throws IOException {
        Cell[][] newGrid = new Cell[height][width];
        for(int row = 0; row < height; row++ ){
            for(int col = 0; col < width; col++ ){
                if (row == targetCellX && col == targetCellY) {
                    directionController(grid, row, col);
                }
                newGrid[row][col] = new Cell(grid[row][col].getNum());
            }
        }
        newGrid[tempArr[1]][tempArr[0]] = new Cell(0);
        newGrid[targetCellY][targetCellX] = new Cell(grid[tempArr[1]][tempArr[0]].getNum());

        targetCellY = tempArr[1];
        targetCellX = tempArr[0];
        grid = newGrid;
    }

    private boolean validateIfPossibleMovement(char step, int[] arr) throws IOException {
        tempArr = setTheUpcomingStep(step, arr);
        if ((tempArr[0] < 4 && tempArr[0] >= 0) &&
                (tempArr[1] < 4 && tempArr[1] >= 0)) {
            return true;
        }
        return false;
    }


    // Check if the path is possible and if so, set the variables for swapping
    private void directionController(Cell[][] generation, int row, int col) throws IOException {
        System.out.print("Your next move: ");
        char step = scanner.next().charAt(0);
        if (Controls.LEFT.getValue() != step &&
            Controls.DOWN.getValue() != step &&
            Controls.RIGHT.getValue() != step &&
            Controls.UP.getValue() != step  &&
            Controls.QUIT.getValue() != step) {

            System.out.println("Only 'a,s,d,w or q' are valid options");
            directionController(generation, row, col);
        }

        int[] arrHolder = new int[]{targetCellX, targetCellY};
        boolean isOutOfBounds = validateIfPossibleMovement(step, arrHolder);
        if(!isOutOfBounds) {
            System.out.println("You are at the border choose another direction");
            directionController(generation, row, col);
        }

    }

    // Set the positions swapping
    private int[] setTheUpcomingStep(char direction, int[] arr) throws IOException {
        if (Controls.LEFT.getValue() == direction)
            arr[0] -= 1;

        if (Controls.RIGHT.getValue() == direction)
            arr[0] += 1;

        if (Controls.UP.getValue() == direction)
            arr[1] -= 1;

        if (Controls.DOWN.getValue() == direction)
            arr[1] += 1;

        if (Controls.QUIT.getValue() == direction)
            FileManager.saveCurrentGrid(grid);

        return arr;
    }
}