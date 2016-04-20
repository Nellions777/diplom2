package NodeClient;

import student.diplom.web.models.Pack;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;


public class ClientNode {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ClientNode clientNode = new ClientNode();
        int port = 777;
        String host = "localhost";
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (ConnectException e) {
            System.out.println("Connection is refused");
            return;
        }
        System.out.println("CONNECTED");

        OutputStream outStreamSocket = socket.getOutputStream();
        InputStream inStreamSocket = socket.getInputStream();

        PrintStream out = new PrintStream(outStreamSocket);
        ObjectInputStream in = new ObjectInputStream(inStreamSocket);

        out.println("Hello");

        Pack pack = (Pack) in.readObject();
        //Calculable calculator = new CalculateSumm();
        Calculable calculator = new CalculateIntegral();
        calculator.calculate(pack);
        // calculateNode.calculate(pack.getParam("a"), pack.getParam("b"), pack.getParam("c"), pack.getParam("s"));
        System.out.println(pack);

        in.close();
        out.close();
        outStreamSocket.close();
        inStreamSocket.close();
    }
}
