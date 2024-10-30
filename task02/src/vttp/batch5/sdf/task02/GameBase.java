
////base code from self practise

package vttp.batch5.sdf.task02;

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
    
    //importing TTTfile for game
    


    public boolean isValidMove(int move) {
        return move >= 1 && move <= 9 && board[move - 1] == EMPTY;
    }

    public void makeMove(int move, char player) {
        board[move - 1] = player;
    }

    // Minimax for optimal AI move
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
        if (checkWin(COMPUTER)) return 10 - depth;
        if (checkWin(PLAYER)) return depth - 10;
        if (!hasEmptyCell()) return 0;

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
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
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
            if ((i + 1) % 3 == 0) sb.append("\n");
            else sb.append("|");
        }
        return sb.toString();
    }

    private boolean hasEmptyCell() {
        for (char cell : board) {
            if (cell == EMPTY) return true;
        }
        return false;
    }

    public boolean isGameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGameOver'");
    }

    public char[] getResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }
}





