package student.diplom.web.server;

import student.diplom.web.models.Pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * Created by Андрей on 28.03.2016.
 */
public class CalculateNode {
    private ObjectOutputStream out;
    private BufferedReader in;

    public CalculateNode(Socket socket) throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendMessage(Pack pack) throws IOException {
        out.writeObject(pack);
    }

    public String getMessage() throws IOException, ClassNotFoundException {
        return in.readLine();
    }

    public void close() {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
