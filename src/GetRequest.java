import java.io.OutputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/17/11
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetRequest implements ResponseType {

    public void serveResponse(OutputStream out, Map request){

    }

    public String getPath(Map request){
        if(request.get("Method") == "/"){
            String path = "index.html";
            return path;
        }
        else
            return null;
    }
}
