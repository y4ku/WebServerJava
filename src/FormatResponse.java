import java.io.*;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/12/11
 * Time: 8:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class FormatResponse {

    Map<String, String> request;
    Map<String, ResponseType> appRoutes;
    private String requestMethod;
    private String path;

    public FormatResponse(Map<String, String> formattedRequest, Map<String, ResponseType> appRoutes){
        this.appRoutes = appRoutes;
        request = formattedRequest;
        this.requestMethod = formattedRequest.get("Method");
        this.path = formattedRequest.get("Request-URI");
    }

    public ResponseType checkResponseType(){
        if(appRoutes.containsKey(path)){
            return appRoutes.get(path);
        }
        else if(requestMethod.equals("GET")){
            return new GetResponse();
        }
        else{
            System.out.println("501 Not Implemented");
            return null;
        }
    }
}
