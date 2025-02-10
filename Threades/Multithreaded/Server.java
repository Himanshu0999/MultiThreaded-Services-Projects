package Threades.Multithreadad;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
import java.io.IOException;
import java.io.PrintWriter;

public class Server {

    public Consumer <Socket> getConsumer(){

    return (clientSocket) ->{
        try{
          PrintWriter toclient = new PrintWriter(clientSocket.getOutputStream());
            toclient.println("Hello from the server");
            toclient.close();
            clientSocket.close();

        }catch (IOException ex) {
            ex.printStackTrace();
     } 
      
   }; 

}

       

    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server();
        try {
            ServerSocket ServerSocket = new ServerSocket(port);
            ServerSocket.setSoTimeout(10000);
            System.out.println("Server started on port " + port);
            while (true) {
                    Socket acceptedConnection = ServerSocket.accept();
                    Thread thread = new Thread(()-> server.getConsumer().accept(acceptedConnection));
                    thread.start();
                    
                
            }      

        } catch (IOException ex) {
            ex.printStackTrace(); 
    }
}
}
