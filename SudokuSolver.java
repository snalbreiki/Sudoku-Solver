import java.util.*;

public class SudokuSolver {
    private static final int GRID_SIZE = 9;

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    static private boolean isValidPlacement(int board[][], int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    public static boolean solveBoard(int board[][]) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    //PRINT METHOD
    public static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            System.out.println("");
            if (row % 3 == 0) {
                System.out.println("# _ _ _ # _ _ _ # _ _ _ #");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][column] + " ");
                if (column % 3 == 2) {
                    System.out.print("| ");
                }
            }
        }
        System.out.println("\n# _ _ _ # _ _ _ # _ _ _ #");
    }

    static private void select(int board[][]) {
        Scanner input = new Scanner(System.in);
        String response = "m";
        int number;
        out: //location refrecnce for breaking nested loop to it
        while (response.equals("m")) {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    System.out.print("Press: (m) to move | (y) to select | (e) to solve: ");
                    response = input.next();
                    if (response.equals("m")) {
                        board[row][column] = 1;
                        printBoard(board);
                    }
                    //printBoard(board);
                    if (response.equals("y")) {
                        board[row][column - 1] = 1;
                        printBoard(board);
                        System.out.print("enter number: ");
                        number = input.nextInt();
                        board[row][column - 1] = number;
                        printBoard(board);
                        column--;
                    }
                    if (response.equals("m")) {
                        board[row][column] = 0;
                    }
                    if (response.equals("e")) {
                        break out;
                    }
                }
            }
        }
    }
}