package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;





public class Main implements Runnable {
    private Socket clientSocket;
    private GameBase game;

    public Main(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.game = new GameBase();
    }


    

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            out.println("Welcome to Tic-Tac-Toe! Enter your move as '1-9'.");
            out.println(game.getBoard());

            String input;
            while ((input = in.readLine()) != null) {
                if (input.equalsIgnoreCase("quit")) {
                    out.println("Game over. Thanks for playing!");
                    break;
                }

                int move;
                try {
                    move = Integer.parseInt(input.trim());
                } catch (NumberFormatException e) {
                    out.println("Invalid input. Enter a number from 1 to 9.");
                    continue;
                }

                if (game.isValidMove(move)) {
                    game.makeMove(move, game.PLAYER);
                    out.println(game.getBoard());

                    if (game.isGameOver()) {
                        out.println(game.getResult());
                        break;
                    }

                    // Server (AI) makes its move using minimax
                    int serverMove = game.getBestMove();
                    game.makeMove(serverMove, game.COMPUTER);
                    out.println("Server's move:");
                    out.println(game.getBoard());

                    if (game.isGameOver()) {
                        out.println(game.getResult());
                        break;
                    }
                } else {
                    out.println("Invalid move. Try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


