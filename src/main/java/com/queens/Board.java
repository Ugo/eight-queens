package com.queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private int[] board;

    Board(int n) {
        board = new int[n];
    }

    private int getElement(int i) {
        return board[i];
    }

    void setElement(int i, int val) {
        board[i] = val;
    }

    private int size() {
        return board.length;
    }

    protected Board clone() {
        Board clone = new Board(size());
        for (int iter = 0; iter < size(); iter++) {
            clone.setElement(iter, getElement(iter));
        }
        return clone;
    }

    private boolean equals(Board board) {
        if (size() != board.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (getElement(i) != board.getElement(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Method to check if a board is valid for the n first lines
     */
    boolean isValid(int n) {
        for (int iterLine = 0; iterLine < n; iterLine++) {
            // same column
            if (board[iterLine] == board[n]) {
                return false;
            }
            // same diagonal
            if (Math.abs((board[n] - board[iterLine])) == (n - iterLine)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to mirror a board
     */
    private Board mirror() {
        Board ret = new Board(size());
        for (int iter = 0; iter < size(); iter++) {
            ret.setElement(iter, size() - getElement(iter) - 1);
        }
        return ret;
    }

    /**
     * Method to rotate a board a quarter clockwise
     */
    private Board rotate() {
        Board ret = new Board(size());
        for (int iter = 0; iter < size(); iter++) {
            ret.setElement(size() - getElement(iter) - 1, iter);
        }
        return ret;
    }

    void print() {
        for (int element : board) {
            for (int col = 0; col < size(); col++) {
                if (col == element) {
                    System.out.print("Q");
                } else {
                    System.out.print("*");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Method to check if a board is already present in a list of boards
     *
     * @param listBoards the list of boards
     * @return true if the board or one of its derived one (rotated/mirrored) is already present, false otherwise
     */
    boolean isPresent(List<Board> listBoards) {

        // from one board, we create all combinations and check if they are
        // already in the list to filter only the unique ones
        Board rotate1 = rotate();
        Board rotate2 = rotate1.rotate();
        Board rotate3 = rotate2.rotate();
        Board mirror1 = mirror();
        Board mirror2 = mirror1.rotate();
        Board mirror3 = mirror2.rotate();
        Board mirror4 = mirror3.rotate();
        List<Board> boardsToTest = new ArrayList<>(Arrays.asList(this, rotate1, rotate2, rotate3,
                mirror1, mirror2, mirror3, mirror4));

        for (Board boardToTest : boardsToTest) {
            for (Board existingBoard : listBoards) {
                if (boardToTest.equals(existingBoard)) {
                    return true;
                }
            }
        }

        return false;
    }
}