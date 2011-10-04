import com.sun.servicetag.SystemEnvironment;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 9/28/11
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class HTTPServer implements Runnable {

    private ServerSocket server = null;
    private Socket socket = null;
    private Thread thread = null;
    private serverTask task = null;
    private boolean accepting = false;
    //private int connections = 0;


    public static void main(String args[]) throws IOException {
        HTTPServer server = new HTTPServer();
        server.createServer(5000, new parseTask());
    }

    public void run(){
        accepting = true;
        while(accepting){
            acceptConnections();
        }
    }

    public void acceptConnections() {
        try{
            System.out.println("SERVER: Waiting for client connection...");
            socket = server.accept();
            System.out.println("SERVER: Client accepted: " + socket);
            task.doTask(socket);
            closeSocket();
        }
        catch (IOException e){
            System.out.println("SERVER: Failed to accept client: " + e);
        }

    }

    public void createServer(int port, serverTask task) {
        try{
            System.out.println("SERVER: Attempting to create server socket on port: " + port);
            server = new ServerSocket(port);
            System.out.println("SERVER: Created Server Socket: " + server);
            this.task = task;
            thread = new Thread(this);
            thread.start();
        }
        catch (IOException e) {
            System.out.println("SERVER: Error in creating socket: " + e);
        }
    }

    public void closeServer() throws IOException {
        if(server != null){
            accepting = false;
            server.close();
            System.out.println("SERVER: Closed Listen Socket");
        }
    }

    public void closeSocket() throws IOException {
        if(socket != null){
            socket.close();
            System.out.println("SERVER: Closed Server-Client Socket");
        }
    }

    public int getSocketPort(){
        if(server != null) return server.getLocalPort();
        else {
            System.out.println("SERVER: Server doesn't exist");
            return 0;
        }
    }


}
