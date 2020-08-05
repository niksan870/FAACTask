package com.company;

import java.util.*;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    private static Grid grid;
    private static int numberOfLoops = 0;
    private static int[] arr = new int[16];
    private static List<Integer> arrayOfNums = new LinkedList (Arrays.asList(15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0));

    // Input method
    public static void setup () {
//        String gridDimensions = "4";
//        String[] gridDimensionsArray = gridDimensions.split("\\s*,\\s*");

        int width = 4;
        int height = 4;
        if (height > 1000 || width > 1000 ) {
            throw new IllegalArgumentException("Opsy, You may need to download some RAM.");
        }

        Cell[][] generationZero = new Cell[height][width];


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int randomNumber = arrayOfNums.get((int)(Math.random() * arrayOfNums.size()));
                generationZero[i][j] = new Cell(randomNumber);
                arrayOfNums.removeIf(s -> s.equals(randomNumber));
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(generationZero[i][j].isGreen());
                System.out.print(' ');
            }
            System.out.println();
        }
//        String cellToBeTracked = scanner.nextLine();
//        String[] arguments = cellToBeTracked.split("\\s*,\\s*");
//
//        if (arguments.length != 3) {
//            throw new IllegalArgumentException("Invalid number of arguments provided. Expected exactly 3 are required");
//        }
//
//        int targetCellX = Integer.parseInt(arguments[0]);
//        int targetCellY = Integer.parseInt(arguments[1]);
//        numberOfLoops = Integer.parseInt(arguments[2]);
//
////        System.out.println(generationZero[targetCellY][targetCellX]);
//        grid = new Grid(generationZero, width, height, targetCellX, targetCellY);
    }

    // Execute the logic
    public static long start () {
////        grid.drawGrid();
//        for (int i = 0; i < numberOfLoops; i++) {
////            Thread.sleep(100);
//            grid.nextGeneration();
////            grid.drawGrid();
//        }
//        System.out.println(grid.getTargetCellMaturity());
//        return grid.getTargetCellMaturity();
        return 1;
    }
}

