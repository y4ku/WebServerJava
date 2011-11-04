
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

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
    Map<String, ResponseType> appRoutes;

    public ServerTask(Socket s, Map<String, ResponseType> appRoutes){
        this.s = s;
        this.appRoutes = appRoutes;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = s.getOutputStream();
        } catch (IOException e) {
            System.out.println("Error opening input or output stream: " + e);
        }
    }


    public void run() {

       /* try {
            out.write("Welcome SagePad. You are Connected to Java Server your arch-nemesis".getBytes());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/

        ReceiveRequest receive = new ReceiveRequest(in);
        FormatResponse format = new FormatResponse(receive.parseRequest(receive.getRequest()), appRoutes);
        ResponseType type = format.checkResponseType();
        if(type != null){
            try {
                out.write(type.serveResponse(format.request));
            } catch (IOException e) {
                System.out.println("Failed at writing OutputStream: " + e);
            }

        }
        else{
            try {
                out.write((new HTTPResponse().getHeader(501)).getBytes());
            } catch (IOException e) {
                System.out.println("Failed at writing OutputStream: " + e);
            }
        }


        try {
            out.close();
            in.close();
            s.close();
        } catch (IOException e) {
            System.out.println("Socket did not close: " + e);
        }

    }
}
