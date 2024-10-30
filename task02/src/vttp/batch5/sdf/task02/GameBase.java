

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

    // importing TTTfile to read
    public void readingTTTFile(String inputDirPath) throws IOException {
        File dir = new File(inputDirPath);

        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalAccessException(
                    "Input director does not exist or is not in directory please check again." + inputDirPath);
        }
        for (File file : dir.listFiles()) {
            if (file != null && file.isFile()) {
                String filePath = file.getAbsolutePath();
                try {
                    // buffered reader to read line by line
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        StringBuilder contentBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            contentBuilder.append(line).append("\n");
                        }
                        System.out.println("Processing file: " + filePath);
                    }

                    //output the what TTT into the grid
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
                        Object contentBuilder;
                        writer.write(filePath + ":" + contentBuilder.toString());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.err.println("Error processing file: " + filePath);
                    e.printStackTrace();
                }
            }
        }
    }

    //for game
    public boolean isGameOver() {
        return checkWin(PLAYER) || checkWin(COMPUTER) || !hasEmptyCell();
    }

    public String getResult() {
        if (checkWin(COMPUTER))
            return "Computer wins!";
        if (checkWin(PLAYER))
            return "You win!";
        return "It's a draw!";
    }

    public boolean isValidMove(int move) {
        return move >= 1 && move <= 9 && board[move - 1] == EMPTY;
    }

    public void makeMove(int move, char player) {
        board[move - 1] = player;
    }

    // Minimax for optimal 'AI' move, making AI build up from what the TTT files are given
    public int getBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == EMPTY) {
                board[i] = COMPUTER;
                int score = minimax(0, false);
                board[i] = EMPTY;
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i + 1;
                }
            }
        }
        return bestMove;
    }

    private int minimax(int depth, boolean isMaximizing) {
        if (checkWin(COMPUTER))
            return 10 - depth;
        if (checkWin(PLAYER))
            return depth - 10;
        if (!hasEmptyCell())
            return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == EMPTY) {
                    board[i] = COMPUTER;
                    int score = minimax(depth + 1, false);
                    board[i] = EMPTY;
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == EMPTY) {
                    board[i] = PLAYER;
                    int score = minimax(depth + 1, true);
                    board[i] = EMPTY;
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    private boolean checkWin(char player) {
        int[][] winConditions = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                { 0, 4, 8 }, { 2, 4, 6 }
        };
        for (int[] condition : winConditions) {
            if (board[condition[0]] == player && board[condition[1]] == player && board[condition[2]] == player) {
                return true;
            }
        }
        return false;
    }

    public String getBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            sb.append(board[i] == EMPTY ? " " : board[i]);
            if ((i + 1) % 3 == 0)
                sb.append("\n");
            else
                sb.append("|");
        }
        return sb.toString();
    }

    private boolean hasEmptyCell() {
        for (char cell : board) {
            if (cell == EMPTY)
                return true;
        }
        return false;
    }

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
        }
    } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }

    private void readAndProcessFiles(String inputDirPath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readAndProcessFiles'");
    }

