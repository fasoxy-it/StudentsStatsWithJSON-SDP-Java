import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]) throws Exception {

        BufferedReader inFromClient;

        Gson gson = new Gson();

        try {
            ServerSocket welcomeSocket = new ServerSocket(6789);

            while (true) {
                Socket connectionSocket = welcomeSocket.accept();
                inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                Student student = gson.fromJson(inFromClient.readLine(), Student.class);
                System.out.println(student.print());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
