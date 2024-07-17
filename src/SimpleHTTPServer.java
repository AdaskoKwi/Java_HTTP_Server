import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class SimpleHTTPServer {
    public static void main(String[] args) throws IOException {
        final ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080...");
        while (true){
            try(Socket socket = server.accept()){
                Date today = new Date();
                String HTTPresponse = "HTTP/1.1/ 200 OK \r\n\r\n" + today;

                socket.getOutputStream()
                        .write(HTTPresponse.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
