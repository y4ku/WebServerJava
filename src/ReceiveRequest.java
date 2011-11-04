import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/3/11
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveRequest {
    BufferedReader in;
    Map<String, String> formattedRequest = new HashMap<String,String>();

    public ReceiveRequest(BufferedReader in){
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
        String [] params = method[1].split("\\?");
        formattedRequest.put("Request-URI", params[0]);
        if(params.length > 1){
            String [] splitParams = params[1].split("&");
            for(int i = 0; i < splitParams.length; i++){
                String [] paramType = splitParams[i].split("=");
                formattedRequest.put("param" + i, paramType[1]);
            }
        }
        formattedRequest.put("HTTP-Version", method[2]);

        return formattedRequest;
    }
}

