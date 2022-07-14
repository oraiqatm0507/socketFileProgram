import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String args[]) throws IOException{
       
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        System.out.println("Client Connected to Server.");
       
        String message = """
            GET /home/friends/temp.txt HTTP/1.1
            User-Agent: PostmanRuntime/7.29.0
            Accept: */*
            Postman-Token: 212284d2-29af-4f68-a95b-42c65fc94b72
            Host: localhost:6666
            Accept-Encoding: gzip, deflate, br
            Connection: keep-alive
            """;
        String response = "";
        response = client.sendMessage(message);
        
        System.out.println("File Content: " + response);
	}
}