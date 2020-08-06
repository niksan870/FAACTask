package com.company;

import java.io.*;

public class FileManager {
    private static int targetCellX = 0;
    private static int targetCellY = 0;

    // Save the grid to file called Grid.txt
    public static void saveCurrentGrid(Cell[][] grid) throws IOException {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                builder.append(grid[i][j].getNum()+"");
                if(j < grid.length - 1)
                    builder.append(",");
            }
            builder.append("\n");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("Grid.txt"));
        writer.write(builder.toString());
        writer.close();
        System.exit(0);
    }

    // Read and construct a Cell 2 D array as well as initializing the appropriate variables
    public static Cell[][] readSavedGrid() throws IOException {
        String savedGameFile = "Grid.txt";
        Cell[][] grid = new Cell[4][4];
        BufferedReader reader = new BufferedReader(new FileReader(savedGameFile));
        String line = "";
        int row = 0;
        while((line = reader.readLine()) != null) {
            String[] cols = line.split(",");
            int col = 0;
            for(String  c : cols) {
                if (Integer.parseInt(c) == 0) {
                    targetCellX = row;
                    targetCellY = col;
                }

                grid[row][col] = new Cell(Integer.parseInt(c));
                col++;
            }
            row++;
        }
        reader.close();

        return grid;
    }

    public static int getTargetCellX() {
        return targetCellX;
    }

    public static void setTargetCellX(int targetCellX) {
        FileManager.targetCellX = targetCellX;
    }

    public static int getTargetCellY() {
        return targetCellY;
    }

    public static void setTargetCellY(int targetCellY) {
        FileManager.targetCellY = targetCellY;
    }
}
