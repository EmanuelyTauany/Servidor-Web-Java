import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class IndexHandler implements HttpHandler{

    public static final String PATH = "/";

    @Override
    public void handle(HttpExchange conn) throws IOException {
        byte[] hello = "Seja bem-vindo(a) a API Balaio de Gato! Temos os seguintes endpoints: /raiz e /maiuscula".getBytes();

        try{
            conn.sendResponseHeaders(HTTP_OK, hello.length);

            Headers headers= conn.getResponseHeaders();

            headers.add("Content-type", "text/html; charset=UTF-8");

            try (OutputStream out = conn.getResponseBody()){
                 out.write(hello);
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            conn.close();
        }
    } 
}
