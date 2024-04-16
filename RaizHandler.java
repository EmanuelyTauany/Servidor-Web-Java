import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RaizHandler implements HttpHandler{

    public static final String PATH= "/raiz";

    @Override
    public void handle(HttpExchange conn) throws IOException {
        String[] partes = conn.getRequestURI().getPath().split("/");

        String parametro = partes[2];
        byte[] result = calculateResponse(parametro);

        try{
            conn.sendResponseHeaders(HTTP_OK, result.length);

            Headers headers = conn.getResponseHeaders();
            headers.add("Content-Type", "text/html; charset=UTF-8");

            try(OutputStream out = conn.getResponseBody()){
                  out.write(result);
            } catch(Exception e){
                e.printStackTrace();

            }
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            conn.close();
        }
    }

    byte[] calculateResponse(String parametro){
      
        double pDouble = Double.parseDouble(parametro);

        double raiz = Math.sqrt(pDouble);
        
        return Double.toString(raiz).getBytes();
    }
}
