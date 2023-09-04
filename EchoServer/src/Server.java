import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2414);
            System.out.println("Accepting connection on port 2414");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection established " + socket.getRemoteSocketAddress());
                handleClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream())
        ) {
            while (true) {
                String incomingText = in.readUTF();
                System.out.println("Text received: " + incomingText);
                out.writeUTF("Thank you for the text - it has been received: " + incomingText);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}