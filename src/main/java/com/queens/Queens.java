package com.queens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Queens {

    /**
     * size of the board used in the test
     */
    private static final int BOARD_SIZE = 8;

    private static Set<int[]> RESULTS;

    /**
     * Main method
     */
    public static void main(String[] args) {
        RESULTS = new HashSet<>();

        int[] board = new int[BOARD_SIZE];
        enumerate(board, 0);

        System.out.println("Number of results: " + RESULTS.size());
        List<int[]> UNIQUE_RESULTS = filterUniqueResults();
        int count = 0;
        for (int[] tempBoard : UNIQUE_RESULTS) {
            count++;
            System.out.println("Unique solution " + count);
            printBoard(tempBoard);
            System.out.println("");
        }

        System.out.println("Number of unique results: " + UNIQUE_RESULTS.size());

    }

    private static List<int[]> filterUniqueResults() {
        List<int[]> uniqueResults = new ArrayList<>();
        for (int[] temp : RESULTS) {

            // from one board, we create all combinations and check if they are
            // already in the list to filter only the unique ones
            int[] rotate1 = rotateBoard(temp);
            int[] rotate2 = rotateBoard(rotate1);
            int[] rotate3 = rotateBoard(rotate2);
            int[] mirrorTemp = mirrorBoard(temp);
            int[] rotateMir1 = rotateBoard(mirrorTemp);
            int[] rotateMir2 = rotateBoard(rotateMir1);
            int[] rotateMir3 = rotateBoard(rotateMir2);

            if (!isAlreadyPresent(temp, uniqueResults) && !isAlreadyPresent(rotate1, uniqueResults)
                    && !isAlreadyPresent(rotate2, uniqueResults) && !isAlreadyPresent(rotate3, uniqueResults)
                    && !isAlreadyPresent(mirrorTemp, uniqueResults) && !isAlreadyPresent(rotateMir1, uniqueResults)
                    && !isAlreadyPresent(rotateMir2, uniqueResults) && !isAlreadyPresent(rotateMir3, uniqueResults)) {
                uniqueResults.add(temp);
            }
        }
        return uniqueResults;
    }

    /**
     * Method to check if a board is already present in a list of boards
     *
     * @param board      the board to check
     * @param listBoards the list of already existing boards
     * @return true if the board is already present, false otherwise
     */
    private static boolean isAlreadyPresent(int[] board, List<int[]> listBoards) {
        for (int[] temp : listBoards) {
            if (equalBoard(board, temp)) {
                return true;
            }
        }
        return false;
    }

    private static boolean equalBoard(int[] board1, int[] board2) {
        if (board1.length != board2.length) {
            return false;
        }
        for (int i = 0; i < board1.length; i++) {
            if (board1[i] != board2[i]) {
                return false;
            }
        }
        return true;
    }

    private static void enumerate(int board[], int startLine) {
        if (startLine == BOARD_SIZE) {
            RESULTS.add(board.clone());
        } else {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[startLine] = col;
                if (isValidBoard(board, startLine)) {
                    enumerate(board, (startLine + 1));
                }
            }
        }
    }

    /**
     * Method to check if a board is valid for the n first lines
     */
    private static boolean isValidBoard(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            // same column
            if (q[i] == q[n]) {
                return false;
            }
            // same diagonal
            if (Math.abs((q[n] - q[i])) == (n - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to mirror a board
     */
    private static int[] mirrorBoard(int[] board) {
        int size = board.length;
        int[] result = new int[size];
        for (int iter = 0; iter < size; iter++) {
            result[iter] = size - board[iter] - 1;
        }
        return result;
    }

    /**
     * Method to rotate a board clockwise
     */
    private static int[] rotateBoard(int[] board) {
        int size = board.length;
        int[] result = new int[size];
        for (int iter = 0; iter < size; iter++) {
            int oldCol = board[iter];
            int newLine = size - 1 - oldCol;
            result[newLine] = iter;
        }
        return result;
    }

    /**
     * Simple method to print a board
     *
     * @param board the board to print
     */
    private static void printBoard(int[] board) {
        int size = board.length;
        for (int aBoard : board) {
            for (int col = 0; col < size; col++) {
                if (col == aBoard) {
                    System.out.print("Q");
                } else {
                    System.out.print("*");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
