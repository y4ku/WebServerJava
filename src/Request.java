import com.sun.servicetag.SystemEnvironment;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/3/11
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Request {
    BufferedReader in;
    Map<String, String> formattedRequest = new HashMap<String,String>();

    public Request(BufferedReader in){
            this.in = in;
    }

    public String getRequest(){
        String request = "";
        try{
            while(!in.ready()){}
            while(in.ready()){
                int nextChar = in.read();
                request += (char)nextChar;
            }
        } catch (IOException e) {
            System.out.println("Failed to read request " + e);
        }

        return request;
    }

    public Map parseRequest(String request){
        String [] lineByline = request.split("\r\n");
        String [] method = lineByline[0].split(" ");

        formattedRequest.put("Method", method[0]);
        formattedRequest.put("Request-URI", method[1]);
        formattedRequest.put("HTTP-Version", method[2]);

        return formattedRequest;
    }
}

