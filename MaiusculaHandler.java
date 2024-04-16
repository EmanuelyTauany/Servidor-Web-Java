import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import static java.net.HttpURLConnection.HTTP_OK;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class MaiusculaHandler implements HttpHandler{
    
    public static final String PATH= "/maiuscula";


    @Override
    public void handle(HttpExchange conn) throws IOException {

        InputStream fluxoCorpoRequisicao = conn.getRequestBody();
        OutputStream fluxoCorpoResposta = conn.getResponseBody();

        Scanner scanner = new Scanner(fluxoCorpoRequisicao);

        try{
            String corpoRequisicao = scanner.nextLine();

            byte[] corpoRequisicaoMaiusculo = corpoRequisicao.toUpperCase().getBytes();

            conn.sendResponseHeaders(HTTP_OK, corpoRequisicaoMaiusculo.length);

            fluxoCorpoResposta.write(corpoRequisicaoMaiusculo);

        }finally{
                conn.close();
                scanner.close();
            
        }
    }
}
