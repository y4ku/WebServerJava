import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/18/11
 * Time: 9:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class HTTPResponse {

        Map<Integer, String> statusCode = new HashMap<Integer, String>(){{
            put(100, "HTTP/1.1 100 Continue\r\n");
            put(101, "HTTP/1.1 101 Switching Protocols\r\n");
            put(200, "HTTP/1.1 200 OK\r\n");
            put(201, "HTTP/1.1 201 Created\r\n");
            put(202, "HTTP/1.1 202 Accepted\r\n");
            put(203, "HTTP/1.1 203 Non-Authoritative Information\r\n");
            put(204, "HTTP/1.1 204 No Content\r\n");
            put(205, "HTTP/1.1 205 Reset Content\r\n");
            put(206, "HTTP/1.1 206 Partial Content\r\n");
            put(300, "HTTP/1.1 300 Multiple Choices\r\n");
            put(301, "HTTP/1.1 301 Moved Permanently\r\n");
            put(302, "HTTP/1.1 302 Found\r\n");
            put(303, "HTTP/1.1 303 See Other\r\n");
            put(304, "HTTP/1.1 304 Not Modified\r\n");
            put(305, "HTTP/1.1 305 Use Proxy\r\n");
            put(307, "HTTP/1.1 307 Temporary Redirect\r\n");
            put(400, "HTTP/1.1 400 Bad Request\r\n");
            put(401, "HTTP/1.1 401 Unauthorized\r\n");
            put(402, "HTTP/1.1 402 Payment Required\r\n");
            put(403, "HTTP/1.1 403 Forbidden\r\n");
            put(404, "HTTP/1.1 404 Not Found\r\n");
            put(405, "HTTP/1.1 405 Method Not Allowed\r\n");
            put(406, "HTTP/1.1 406 Not Acceptable\r\n");
            put(407, "HTTP/1.1 407 Proxy Authentication Required\r\n");
            put(408, "HTTP/1.1 408 Request Time-Out\r\n");
            put(409, "HTTP/1.1 409 Conflict\r\n");
            put(410, "HTTP/1.1 410 Gone\r\n");
            put(411, "HTTP/1.1 411 Length Required\r\n");
            put(412, "HTTP/1.1 412 Precondition Failed\r\n");
            put(413, "HTTP/1.1 413 Request Entity Too Large\r\n");
            put(414, "HTTP/1.1 414 Request-URI Too Large\r\n");
            put(415, "HTTP/1.1 415 Unsupported Media Type\r\n");
            put(416, "HTTP/1.1 416 Requested Range Not Satisfiable\r\n");
            put(417, "HTTP/1.1 417 Expectation Failed\r\n");
            put(500, "HTTP/1.1 500 Internal Server Error\r\n");
            put(501, "HTTP/1.1 501 Not Implemented\r\n");
            put(502, "HTTP/1.1 502 Bad Gateway\r\n");
            put(503, "HTTP/1.1 503 Service Unavailable\r\n");
            put(504, "HTTP/1.1 504 Gateway Time-Out\r\n");
            put(505, "HTTP/1.1 505 HTTP Version Not Supported\r\n");
        }};

        public String getHeader(int type){
            return statusCode.get(type) + "Content-Type: text/html; charset=UTF-8\r\n\r\n";
        }

}
