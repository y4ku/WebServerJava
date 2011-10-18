import java.io.*;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/12/11
 * Time: 8:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Response {

    Map<String, String> request;
    private String requestMethod;
    private String path;

    public Response(Map<String, String> formattedRequest){
        request = formattedRequest;
        this.requestMethod = formattedRequest.get("Method");
        this.path = formattedRequest.get("Request-URI");
    }

    public ResponseType checkResponseType(){
        if(requestMethod.equals("GET")){
            return new GetRequest();
        }
        else{
            System.out.println("501 Not Implemented");
            return null;
        }
    }
}
