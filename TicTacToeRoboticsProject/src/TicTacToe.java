import java.util.Scanner;// pull in scanner

public class TicTacToe {
    private char[][] board = {// create the playing bored
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private char currentPlayer = 'X';
    private char computerPlayer = 'O';
    
    public TicTacToe() {
    	
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        makeComputerMove();

        while (true) {
            printBoard();// display the bored

      
            int row = scanner.nextInt() - 1;// take the players input
            int col = scanner.nextInt() - 1;

            if (isValidMove(row, col)) {// checks if the move is valid
                board[row][col] = currentPlayer;
                if (isWinner(board, currentPlayer)) {// checks for wins or draws
                    printBoard();
                    break;
                } else if (isBoardFull(board)) {
                    printBoard();
                    break;
                }
                currentPlayer = 'O';
            } else {
                // checks for move validity
            }

            printBoard();// print the updated board

      
            makeComputerMove();// computer makes a move
            if (isWinner(board, computerPlayer)) {// checks if anyone won yet
                printBoard();
                break;
            } else if (isBoardFull(board)) {
                printBoard();
                break;
            }
            currentPlayer = 'X';
        }

        scanner.close();
    }

    private void makeComputerMove(){ // make the computer have a go
        int[] bestMove = minimax(board, computerPlayer);
        board[bestMove[0]][bestMove[1]] = computerPlayer;
    }
    private int[] minimax(char[][] currentBoard, char player) {// minimax algorithm implementation
        int[] result = new int[]{-1, -1, (player == computerPlayer) ? Integer.MIN_VALUE : Integer.MAX_VALUE};
        char opponent = (player == 'X') ? 'O' : 'X';

        if (isWinner(currentBoard, computerPlayer)) {
            result[2] = 1;
            return result;
        }

        if (isWinner(currentBoard, 'X')) {
            result[2] = -1;
            return result;
        }

        if (isBoardFull(currentBoard)) {
            return result;
        }

        result[2] = (player == computerPlayer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentBoard[i][j] == ' ') {
                    currentBoard[i][j] = player;
                    int[] score = minimax(currentBoard, (player == 'X') ? 'O' : 'X');
                    currentBoard[i][j] = ' ';

                    if (player == computerPlayer) {
                        if (score[2] > result[2]) {
                            result[0] = i;
                            result[1] = j;
                            result[2] = score[2];
                        }
                    } else {
                        if (score[2] < result[2]) {
                            result[0] = i;
                            result[1] = j;
                            result[2] = score[2];
                        }
                    }
                }
            }
        }

        return result;
    }

    private void printBoard() {// method for displaying the board and drawing the grid lines
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean isValidMove(int row, int col) {// checks move validity
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private boolean isBoardFull(char[][] currentBoard) {// checks if all the spaces are full
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinner(char[][] currentBoard, char currentPlayer) {// checks if there is a row of three on any rows,columns or diagonals
 
        for (int i = 0; i < 3; i++) {
            if (currentBoard[i][0] == currentPlayer && currentBoard[i][1] == currentPlayer && currentBoard[i][2] == currentPlayer) {
                return true;
            }
            if (currentBoard[0][i] == currentPlayer && currentBoard[1][i] == currentPlayer && currentBoard[2][i] == currentPlayer) {
                return true; 
            }
        }

        // Check diagonals
        if (currentBoard[0][0] == currentPlayer && currentBoard[1][1] == currentPlayer && currentBoard[2][2] == currentPlayer) {
            return true;
        }
        if (currentBoard[0][2] == currentPlayer && currentBoard[1][1] == currentPlayer && currentBoard[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }
}