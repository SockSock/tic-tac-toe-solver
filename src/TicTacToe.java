import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.utility.Delay;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.robotics.pathfinding.ShortestPathFinder;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Navigator;
import lejos.hardware.sensor.EV3ColorSensor;

public class TicTacToe {
    private char[][] board = { // create the playing board
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private char currentPlayer = 'X'; // Red
    private char computerPlayer = 'O'; // Blue
    private float[] destination = new float[2];
    
    public TicTacToe() {
    	
    }

    public void start(float[] coords) {
    		printBoard();// display the board
            
            int row = Math.round(coords[0]);
            int col = Math.round(coords[1]);

            if (isValidMove(row, col)) {// checks if the move is valid
                 board[row][col] = currentPlayer;
                 if (isWinner(board, currentPlayer)) {// checks for wins or draws
                     printBoard();
                     System.out.println("Player X wins!");
                     Delay.msDelay(5000);
                     System.exit(-1);
                 } else if (isBoardFull(board)) {
                     printBoard();
                     System.out.println("It's a draw!");
                     Delay.msDelay(5000);
                     System.exit(-1);
                 }
                 currentPlayer = 'O';
             } else {
                  // Check if the move is valid
               }
            
            makeComputerMove(); // computer makes a move

            printBoard();// print the updated board

           if (isWinner(board, computerPlayer)) {// checks if anyone won yet
               printBoard();
               System.out.println("Player O wins!");
               Delay.msDelay(5000);
               System.exit(-1);
           } else if (isBoardFull(board)) {
               printBoard();
               System.out.println("It's a draw!");
               Delay.msDelay(5000);
               System.exit(-1);
           }
           currentPlayer = 'X';
    }

    private void makeComputerMove(){ // make the computer have a go
        int[] bestMove = minimax(board, computerPlayer);
        board[bestMove[0]][bestMove[1]] = computerPlayer;
        calculatePosition(bestMove[0], bestMove[1]);
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

    public boolean isValidMove(int row, int col) {// checks move validity
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
    
    public void calculatePosition(int i, int j) {
	    float[] coords = new float[2];
	    
	  
	    destination = coords;
	}
    
    public float[] getDestination() {
    	return destination;
    }
}