import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/18/11
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetResponseTest {
        Map<String, String> index = new HashMap<String,String>();
        Map<String, String> randomPath = new HashMap<String,String>();

    @Before
    public void initialize(){
        index.put("Method", "GET");
        index.put("Request-URI", "/");
        index.put("HTTP-Version", "HTTP/1.1");

        randomPath.put("Method", "GET");
        randomPath.put("Request-URI", "/thisispath/wooh/moose");
        randomPath.put("HTTP-Version", "HTTP/1.1");

    }

    @Test
    public void testGetPath(){

        assertEquals("index.html", (new GetResponse()).getPath(index));
        assertEquals("/thisispath/wooh/moose", (new GetResponse()).getPath(randomPath));

    }

    @Test
    public void testServeResponse(){

    }



}
