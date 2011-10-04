import com.sun.servicetag.SystemEnvironment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/3/11
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class parseTask implements serverTask {
    public void doTask(Socket s) {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                    String input;
                    String request = null;
                    String path = null;
                    if((input = in.readLine()) != null){
                        try {
                            StringTokenizer st = new StringTokenizer(input, " ");
                            request = st.nextToken();
                            path = st.nextToken();
                        } catch (Exception e) {
                            out.println("HTTP/1.1 400 Bad Request\n");
                        }
                        if (request.equals("GET")) {
                            out.println("HTTP/1.1 200 OK");
                            System.out.println("HTTP/1.1 200 OK");
                            System.out.println("You want to: " + request + " this: " + path);
                        }
                        else out.println ("HTTP/1.1 501 Not Implemented\n");
                    }
                    in.close();
                    out.close();
                } catch (IOException e) {
                    System.out.println("Couldn't open PrintWriter or BufferedReader: " + e);
                }
    }


}

