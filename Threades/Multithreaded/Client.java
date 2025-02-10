package Threades.Multithreadad; 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable(){
        return new Runnable() {
            @Override
            public void run(){
             int port = 8080;
             try{
                InetAddress address = InetAddress.getByName("localhost");
                Socket Socket = new Socket(address, port);
                try{
                    PrintWriter toSocket = new PrintWriter(Socket.getOutputStream(), true);
                    BufferedReader fromSocket = new BufferedReader(new InputStreamReader(Socket.getInputStream()));                   
                
                    toSocket.println("Hello from the client" +Socket.getLocalSocketAddress());
                    String line = fromSocket.readLine();
                    System.out.println("Response from server is : " + line);
                    
                }catch (Exception e) {
                    e.printStackTrace();
                } 
            
              }catch (Exception e) {
                e.printStackTrace();
           }
            
        }
    };

 }


  public static void main(String[] args) {
    Client client = new Client();
    for(int i = 0; i<100; i++) {
      
        try {
            Thread thread = new Thread(client.getRunnable());
            thread.start();
    
        } catch (Exception ex) {
          return;

     }
    }
  }
}
