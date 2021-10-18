package cs.vsu.ru.kapustin;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int size = readSize();

        printFigure(size);
    }

    private static int readSize() {
        Scanner scn = new Scanner(System.in);
        int size;
        System.out.print("Enter the size of the figure: ");

        while (!scn.hasNextInt()) {
            System.out.print("Invalid value! Please, try again: ");
            scn.next();
        }
        size = scn.nextInt();

        if (!isNumberCorrect(size)) {
            return readSize();
        }

        return size;
    }

    private static boolean isNumberCorrect(int size) {
        if (size < 3) {
            System.out.println("Error! The number must be at least 3. Please, try again...");
            return false;
        }

        if (size % 2 == 0) {
            System.out.println("Error! The number must be odd. Please, try again...");
            return false;
        }

        return true;
    }

    private static void printFigure(int size) {
        int middleOfFigure = (size / 2) + 1;
        int spacesOutsideFigure = (size / 2);
        int spacesInsideFigure = 0;
        int amountOfNumbersPrinted = 0;
        int requiredAmountOfNumbers;
        int lastNum = 0;

        for (int height = 1; height <= size; height++) {
            if (height == 1 || height == size) {
                requiredAmountOfNumbers = 1;

                printSpaces(spacesOutsideFigure);
                lastNum = printNumbersAndReturnLast(requiredAmountOfNumbers, amountOfNumbersPrinted, lastNum);
            } else if (height < middleOfFigure) {
                spacesOutsideFigure--;

                lastNum = printTrapezoidAndReturnLastNum(lastNum, spacesOutsideFigure, spacesInsideFigure);

                spacesInsideFigure++;
            } else if (height == middleOfFigure) {
                requiredAmountOfNumbers = size;

                lastNum = printNumbersAndReturnLast(requiredAmountOfNumbers, amountOfNumbersPrinted, lastNum);
            } else {
                spacesInsideFigure--;

                lastNum = printTrapezoidAndReturnLastNum(lastNum, spacesOutsideFigure, spacesInsideFigure);

                spacesOutsideFigure++;
            }
            System.out.println();
        }
    }

    private static int printTrapezoidAndReturnLastNum(int lastNum, int spacesOutsideFigure, int spacesInsideFigure) {
        int requiredAmountOfNumbers = 1;
        int amountOfNumbersPrinted = 0;

        printSpaces(spacesOutsideFigure);
        lastNum = printNumbersAndReturnLast(requiredAmountOfNumbers, amountOfNumbersPrinted, lastNum);
        printSpaces(spacesInsideFigure);
        lastNum = printNumbersAndReturnLast(requiredAmountOfNumbers, amountOfNumbersPrinted, lastNum);
        printSpaces(spacesInsideFigure);
        lastNum = printNumbersAndReturnLast(requiredAmountOfNumbers, amountOfNumbersPrinted, lastNum);

        return lastNum;
    }

    private static void printSpaces(int spaces) {
        for (int i = 1; i <= spaces; i++) {
            System.out.print(" ");
        }
    }

    private static int printNumbersAndReturnLast(int requiredAmountOfNumbers, int amountOfNumbersPrinted, int lastNum) {
        for (int i = lastNum; amountOfNumbersPrinted < requiredAmountOfNumbers; i++) {
            System.out.print(i);

            amountOfNumbersPrinted++;
            lastNum++;

            if (lastNum == 10) {
                lastNum = 0;
                return printNumbersAndReturnLast(requiredAmountOfNumbers, amountOfNumbersPrinted, lastNum);
            }
        }
        return lastNum;
    }
}
