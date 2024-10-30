
////base code from self practise

package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class GameBase {

    private char[] board;
    private final char EMPTY = '.';
    final char PLAYER = 'X';
    final char COMPUTER = 'O';

    public GameBase() {
        board = new char[9];
        Arrays.fill(board, EMPTY);
    }
    
    //importing TTTfile to read
    public void readingTTTFile(String inputDirPath) throws IOException {
        File dir = new File(inputDirPath);

        if (!dir.exists() || !dir.isDirectory()){
            throw new
            IllegalAccessException("Input director does not exist or is not in directory please check again." + inputDirPath);
        }
       for (File file : dir.listFiles()){
        if (file !=null && file.isFile()) {
            String filePath = file.getAbsolutePath();
            try{
                //buffered reader to read line by line
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        StringBuilder contentBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            contentBuilder.append(line).append("\n");
                        }
                        System.out.println("Processing file: " + filePath);
                        // Process the content as needed
                    }

                    // Write processed content to output file (for demonstration)
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
                        writer.write(filePath + ": " + contentBuilder.toString());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.err.println("Error processing file: " + filePath);
                    e.printStackTrace();
                }
            }
        }
    }


    public boolean isValidMove(int move) {
        return move >= 1 && move <= 9 && board[move - 1] == EMPTY;
    }

    public void makeMove(int move, char player) {
        board[move - 1] = player;
    }

    // Minimax for optimal AI move
    int getBestMove() {
        return 0; // Placeholder for minimax logic
    }

    // Utility method to make a move
    private void makeMove(int index, char player) {
        if (board[index] == EMPTY) {
            board[index] = player;
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }

    // Check if the game is over
    boolean isGameOver() {
        return false; // Placeholder for win/loss/draw conditions
    }

    // Print the current state of the board
    public String getBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            sb.append(board[i] == EMPTY ? " " : board[i]);
            if ((i + 1) % 3 == 0) sb.append("\n");
            else sb.append("|");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            GameBase game = new GameBase();
            // Directory containing TTT files
            String inputDirPath = "/Users/joshuayeo/Desktop/vttp_b5_assessment_template1/task02/TTT";
            game.readAndProcessFiles(inputDirPath);

            // Example of playing a round
            while (!game.isGameOver()) {
                System.out.println("Computer's move:");
                int bestMove = game.getBestMove();
                game.makeMove(bestMove, game.COMPUTER);
                System.out.println(game.getBoard());
                if (game.checkWin(game.COMPUTER)) {
                    System.out.println("Computer wins!");
                    break;
                }

                // Player's turn
                String input = System.console().readLine("Enter your move: ");
                int playerMove = Integer.parseInt(input) - 1; // Assuming valid input
                game.makeMove(playerMove, game.PLAYER);
                System.out.println(game.getBoard());
                if (game.checkWin(game.PLAYER)) {
                    System.out.println("Player wins!");
                    break;
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }

    // Utility method to check if the computer has won
    private boolean checkWin(char player) {
        return false; // Placeholder for win condition logic
    }

    public char[] getResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }
}