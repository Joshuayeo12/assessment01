package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class Client {
public static void main(String[] args) throws IOException {
        
        int port = 1000;
        if (args.length > 0) {
            String[] split = args[0].split(":");
            port = Integer.parseInt(split[1]);
        }
        System.out.println("Connecting to server...");
        
        String ip = "localhost"; 
        Socket sock = new Socket(ip, port);
        System.out.println("Connected!");
        
        Console cons = System.console();
        if (cons == null) {
            System.err.println("No console available");
            System.exit(1);
        }

        OutputStream outputS = sock.getOutputStream();
        Writer writer = new OutputStreamWriter(outputS);
        BufferedWriter bWriter = new BufferedWriter(writer);
        
        InputStream inputS = sock.getInputStream();
        Reader reader = new InputStreamReader(inputS);
        BufferedReader bReader = new BufferedReader(reader);

        String serverWelcome = bReader.readLine();
        System.out.println(serverWelcome);


    
    
}
}
