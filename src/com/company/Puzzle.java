package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Puzzle {
    private static Scanner scanner = new Scanner(System.in);
    private static Grid grid;
    private static int targetCellX = 0;
    private static int targetCellY = 0;

    // Set number of movements
    private static int numberOfLoops = 10;
    private static int[] arr = new int[16];
    private static List<Integer> arrayOfNums = new LinkedList (Arrays.asList(15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0));

    // Set up the generationArray
    public static void setup () throws IOException {
        File f = new File("Grid.txt");
        Cell[][] generationZero;
        if(f.exists() && !f.isDirectory()) {
            generationZero = FileManager.readSavedGrid();
            targetCellX = FileManager.getTargetCellX();
            targetCellY = FileManager.getTargetCellY();
            f.delete();
        } else {
            generationZero = setNewGrid();
        }
        grid = new Grid(generationZero, targetCellX, targetCellY);
        start();
    }

    // Generate a new random grid
    public static Cell[][] setNewGrid(){
        Cell[][] generationZero = new Cell[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int randomNumber = arrayOfNums.get((int)(Math.random() * arrayOfNums.size()));
                if (randomNumber == 0) {
                    targetCellX = j;
                    targetCellY = i;
                }
                generationZero[i][j] = new Cell(randomNumber);
                arrayOfNums.removeIf(s -> s.equals(randomNumber));
            }
        }
        return generationZero;
    }

    // Execute the for loop until
    public static long start () throws IOException {
        grid.drawGrid();
        for (int i = 0; i < numberOfLoops; i++) {
            grid.nextGeneration();
            grid.drawGrid();
        }
        instructionsAfterTheGameIsOver();
        return 1;
    }

    // On repeat instruction if the player has finished playing
    public static void instructionsAfterTheGameIsOver() throws IOException {
        System.out.println("Would like to play again ? (type y for Yes or q for Quit game)");
        char step = scanner.next().charAt(0);
        if (Controls.YES.getValue() == step) {
            setup();
        } else if (Controls.QUIT.getValue() == step) {
            System.out.println("Bye, bye");
            System.exit(0);
        } else {
            System.out.println("You have to use (y) or (q)");
            arrayOfNums = new LinkedList (Arrays.asList(15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0));
            instructionsAfterTheGameIsOver();
        }
    }
}

