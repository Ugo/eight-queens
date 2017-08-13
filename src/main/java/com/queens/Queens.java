package com.queens;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Queens {

    //size of the board used in the test
    private static final int BOARD_SIZE = 8;

    private static Set<Board> RESULTS = new HashSet<>();

    /**
     * Main method to launch the boards retrieval
     */
    public static void main(String[] args) {

        // perform backtrack
        backtrack(new Board(BOARD_SIZE), 0);

        System.out.println("Total number of results: " + RESULTS.size());

        AtomicInteger count = new AtomicInteger();
        getUniqueResults().forEach(board -> {
            System.out.println("Unique solution n° " + count.incrementAndGet());
            board.print();
            System.out.println("");
        });

        System.out.println("Number of unique results: " + count.get());
    }

    /**
     * Method performing the backtrack algorithm
     */
    private static void backtrack(Board board, int startLine) {
        if (startLine == BOARD_SIZE) {
            RESULTS.add(board.clone());
        } else {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board.setElement(startLine, col);
                if (board.isValid(startLine)) {
                    backtrack(board, (startLine + 1));
                }
            }
        }
    }

    /**
     * Simple method to extract unique boards
     *
     * @return the list of unique results
     */
    private static List<Board> getUniqueResults() {
        List<Board> uniqueResults = new ArrayList<>();
        for (Board board : RESULTS) {
            if (!board.isPresent(uniqueResults)) {
                uniqueResults.add(board);
            }
        }
        return uniqueResults;
    }
}
