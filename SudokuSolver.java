import java.util.Scanner;

public class SudokuSolver {

    public static boolean isSafe(char[][] board, int row, int col, int num) {
        char ch = (char)(num + '0');
        
        // horizontal
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == ch || board[i][col] == ch) {
                return false;
            }
        }

        // vertical
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i <startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == ch) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean solver(char[][] board, int row, int col) {
        if (row == 9) { return true; }

        int nrow = row;
        int ncol = col + 1;
        if (ncol == board.length) {
            nrow = row + 1;
            ncol = 0;
        }

        if (board[row][col] != '.') {
            return solver(board, nrow, ncol);
        }

        for (int i = 1; i <= 9; i++) {
            if (isSafe(board, row, col, i)) {
                board[row][col] = (char)(i + '0');
                if (solver(board, nrow, ncol)) { //backtracking starts from here
                    return true;
                }
                else
                {
                board[row][col] = '.'; //backtrack
                } 
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        solver(board, 0, 0);
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i <9; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[9][9];

        System.out.println("Enter the string of length 9like for example 2..4..2.. ");

        for (int i = 0; i < 9; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        SudokuSolver solver = new SudokuSolver();
        if (solver.solver(board, 0, 0)) {
            System.out.println("Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given Sudoku.");
        }

        sc.close();
    }
}
