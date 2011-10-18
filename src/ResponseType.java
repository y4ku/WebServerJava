import java.io.OutputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/17/11
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ResponseType {

    public static final String ROOT = System.getProperty("user.dir") + "/"  + "pages";

    void serveResponse(OutputStream out, Map request);

}
