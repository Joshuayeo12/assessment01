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
    



}
