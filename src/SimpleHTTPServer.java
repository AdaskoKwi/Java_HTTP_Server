import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
public class SimpleHTTPServer {
    public static void main(String[] args) throws IOException {
        SimpleHTTPServer server = new SimpleHTTPServer();
        final ServerSocket myServer = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080...");
        while (true) {
            try (Socket client = myServer.accept()) {
                server.sendPage(client);
            } catch (IOException e) {
                System.out.println("Something went wrong streaming the page");
                System.exit(1);
            }
        }
    }
    private void sendPage (Socket client) throws IOException {
        System.out.println("Page writter called");

        File index = new File("src/js/index.html");

        PrintWriter printWriter = new PrintWriter(client.getOutputStream());// Make a writer for the output stream to
        // the client
        BufferedReader reader = new BufferedReader(new FileReader(index));// grab a file and put it into the buffer
        // print HTTP headers
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html");
        printWriter.println("Content-Length: " + index.length());
        printWriter.println("\r\n");
        String line = reader.readLine();// line to go line by line from file
        while (line != null)// repeat till the file is read
        {
            printWriter.println(line);// print current line

            line = reader.readLine();// read next line
        }
        reader.close();// close the reader
        printWriter.close();
    }
}
