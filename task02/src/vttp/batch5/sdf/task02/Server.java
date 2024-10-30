package vttp.batch5.sdf.task02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {

        ExecutorService threadpool = Executors.newFixedThreadPool(4);

        int port = 1000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        ServerSocket server = new ServerSocket(port);
        System.out.println("Waiting for connection...");

        while (true) {
            Socket sock = server.accept();
            System.out.println("Got a connection!");

            //putting a threaded server to play the game through the server from the main file



        }
    }
}
