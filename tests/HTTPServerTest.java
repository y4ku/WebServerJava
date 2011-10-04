import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 9/28/11
 * Time: 8:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class HTTPServerTest extends TestCase {
    private int connections = 0;
    private serverTask numConnections = null;


    public HTTPServerTest(String name){
        super(name);
        numConnections = new serverTask() {
            public void doTask(Socket s){
                connections++;
            }
        };
    }

    public static void testWiring(){
        assertEquals(1,1);
    }


    public void testManyConnections() throws Exception
    {
        HTTPServer server = new HTTPServer();
        server.createServer(5000, numConnections);
        for(int i = 0; i < 10; i++){
            connect(5000);
        }
        assertEquals(10, connections);
        server.closeServer();
    }

    public void test_can_accept() throws IOException, InterruptedException {
        HTTPServer server = new HTTPServer();
        server.createServer(5000, numConnections);
        assertEquals(5000, server.getSocketPort());
        connect(5000);
        assertEquals(1, connections);
        server.closeServer();
    }

    public void test_get_test() throws IOException, InterruptedException {
        HTTPServer server = new HTTPServer();
        server.createServer(5000, new parseTask());
        String reply = get_connect(5000);
        assertEquals(reply, "HTTP/1.0 200 OK");
        server.closeServer();
    }


    public void connect(int port) throws InterruptedException {
        try {
            InetAddress host = InetAddress.getLocalHost();
            Socket s = new Socket(host.getHostName(), port);
            try{
                sleep(100);
            }
            catch (InterruptedException e) {
                fail("Couldn't Connect");
            }
            System.out.println("I'm creating socket: " + s);
            s.close();
            System.out.println("CLIENT: Closed Socket");
        }
        catch (IOException e) {
            fail("could not connect");
        }
    }

    public String get_connect(int port) throws InterruptedException {
        try {
            InetAddress host = InetAddress.getLocalHost();
            Socket s = new Socket(host.getHostName(), port);
            System.out.println("I'm creating socket: " + s);
            try{
                sleep(100);
            }
            catch (InterruptedException e) {
                fail("Couldn't Connect");
            }
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("GET /");
            String reply = in.readLine();
            System.out.println("echo: " + reply);
            s.close();
            System.out.println("CLIENT: Closed Socket");
            return reply;
        }
        catch (IOException e) {
            fail("could not connect");
            return null;
        }
    }

}

