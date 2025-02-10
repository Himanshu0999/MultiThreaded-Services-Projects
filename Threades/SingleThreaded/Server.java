package SingleThreaded; 

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Server { 

    public void run () throws IOException {
        int port = 8010; 
        ServerSocket socket = new ServerSocket (port);
        socket.setSoTimeout (10000);
    
        while (true) {
            
            try {
            System.out.println("server is listening on port " + port);
            socket accptedconnection = Socket.accept();
            System.out.println("connection accepted from clint "+ accptedconnection.getRemoteSocketAddress());

            PrintWriter toClient = new PrintWriter(accptedconnection.getOutputStream );
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(accptedconnection.getOutputStream()));

            toClient.println("Hello from the server");

            toClient.close();
            fromClient.close();
            accptedconnection.close();
            }catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    
    }
    
    public static void main ( String [] args) {
     Server server = new Server();
     try {
        server.run();
        } catch (Exception ex) {
        ex.printStackTrace();
        }
    
      }
    
    }


    
    

