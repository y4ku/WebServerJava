import java.io.IOException;
import java.io.InvalidObjectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

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
    private ThreadGroup threads = null;
    private boolean accepting = false;
    private int connections;
    private int port;
    private Map<String, ResponseType> appRoutes = new HashMap<String, ResponseType>();

    public HTTPServer(int port){
        this.port = port;
        this.connections = 0;
        this.threads = new ThreadGroup("threads");
    }

    public void run(){
        try {
            createServer(port);
            accepting = true;
            while(accepting){
                socket = null;
                socket = server.accept();
                new Thread(threads, new ServerTask(socket, appRoutes)).start();
                System.out.println("Number of connections: " + ++connections);
            }
        } catch (IOException e) { }

    }

    public int getConnections(){
        return this.connections;
    }

    public void start() {
        new Thread(this).start();
    }

    public void createServer(int port) {
        try{
            System.out.println("SERVER: Attempting to create server socket on port: " + port);
            server = new ServerSocket(port);
            System.out.println("SERVER: Created Server Socket: " + server);
        }
        catch (IOException e) {
            System.out.println("SERVER: Error in creating socket: " + e);
        }
    }

    public void closeServer() throws IOException {
        accepting = false;
        while(threads.activeCount() != 0){}
        System.out.println("THREADS ARE DONE");
        if(server != null) server.close();
        server = null;
        System.out.println("Server is Closed");
    }

    public int getSocketPort(){
        if(server != null) return server.getLocalPort();
        else {
            System.out.println("SERVER: Server doesn't exist");
            return 0;
        }
    }

    public boolean isActive(){
        if(server == null) return false;
        else return true;
    }

    public void addApp(String location, ResponseType app){
        appRoutes.put(location, app);
    }

    public ResponseType hasApp(String location){
        if(appRoutes.containsKey(location))
            return appRoutes.get(location);
        else
            return null;
    }

    public static void main(String args[]) throws IOException {
        HTTPServer server = new HTTPServer(5000);

        server.start();
    }
}
