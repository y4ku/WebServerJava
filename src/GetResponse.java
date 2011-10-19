import java.io.*;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/17/11
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetResponse implements ResponseType {

    String path;

    public byte[] serveResponse(Map<String,String> request){

        File file = new File(("pages/" + getPath(request)));
        if(file.isFile() && file.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(file);
                byte [] readFile = new byte[(int) file.length()];
                inputStream.read(readFile);
                return prependHeader(200, readFile);

            } catch (Exception e) {
                System.out.println("Read from File False : " + e);
            }
        }
        else{
            return prependHeader(404, "<html><body><h1>File Not Found</h1></body></html>".getBytes());
        }
        return new HTTPResponse().getHeader(500).getBytes();
    }

    public String getPath(Map request){
        if(request.get("Request-URI").equals("/")){
            path = "index.html";
            return path;
        }
        else
            return path = (String) request.get("Request-URI");
    }

    public byte[] prependHeader(int status, byte [] data){
        byte [] header = new HTTPResponse().getHeader(status).getBytes();
        byte[] complete = new byte[header.length + data.length];
        System.arraycopy(header, 0, complete, 0, header.length);
        System.arraycopy(data, 0, complete, header.length, data.length);
        return complete;
    }
}
