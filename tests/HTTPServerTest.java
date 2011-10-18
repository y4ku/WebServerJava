import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
public class HTTPServerTest /*extends TestCase*/ {
    private HTTPServer server;

    @Before
    public void initialize(){
        server = new HTTPServer(5000);
    }


    @Test
    public void testExists() throws InterruptedException, IOException {
        server.start();
        sleep(100);
        assertEquals(server.getSocketPort(), 5000);
        server.closeServer();
        waitForClose();
    }


    @Test
    public void test_can_accept() throws IOException, InterruptedException {
        //assertEquals(5000, server.getSocketPort());
        server.start();
        sleep(100);
        get_connect(5000);
        assertEquals(1, server.getConnections());
        server.closeServer();
        waitForClose();
    }

    @Test
    public void testManyConnections() throws Exception {
        server.start();
        sleep(100);
        for(int i = 0; i < 10; i++){
            get_connect(5000);
        }
        sleep(100);
        assertEquals(10, server.getConnections());
        server.closeServer();
        waitForClose();
    }

    @Test
    public void test_get_test() throws IOException, InterruptedException {
        server.start();
        sleep(100);
        String reply = get_connect(5000);
        assertEquals(reply, "HTTP/1.1 200 OK");
        server.closeServer();
        waitForClose();
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
            out.println("GET / HTTP/1.1");
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

    public boolean waitForClose(){
        while(server.isActive()){}
        return true;
    }
}
