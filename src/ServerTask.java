import com.sun.servicetag.SystemEnvironment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/18/11
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServerTask implements Runnable {

    Socket s;
    BufferedReader in;
    OutputStream out;

    public ServerTask(Socket s){
        this.s = s;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = s.getOutputStream();
        } catch (IOException e) {
            System.out.println("Error opening input or output stream: " + e);
        }
    }


    public void run() {

    }
}
