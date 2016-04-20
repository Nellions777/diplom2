package student.diplom.web.server;

import org.springframework.stereotype.Component;
import student.diplom.web.models.Pack;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Андрей on 04.04.2016.
 */
@Component
public class ManagerNode {
    public static Integer CLIENT_MAX = 100;
    public static boolean isStarted = false;
    private static Queue<CalculateNode> clients = new LinkedList<>();
    private ClientCollectorThread clientCollectorThread;
    private PackSenderThread packSenderThread;
    private ServerSocket serverSocket = null;

    public ManagerNode() throws IOException {
        clientCollectorThread = new ClientCollectorThread();
        packSenderThread = new PackSenderThread();
    }

    public static Integer getCountClients() {
        return clients.size();
    }

    private synchronized CalculateNode getCalculateNode() {
        CalculateNode calculateNode = null;
        while (calculateNode == null) {
            calculateNode = clients.poll();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return calculateNode;
    }

    public void createServerSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startCollect() {
        clientCollectorThread.start();
    }

    public void stopCollect() {
        isStarted = false;
        for (CalculateNode calculateNode : clients) {
            calculateNode.close();
        }
        clients = new LinkedList<CalculateNode>();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientCollectorThread = new ClientCollectorThread();
        packSenderThread.stop();
        packSenderThread = new PackSenderThread();
    }

    public void sendPackages(List<Pack> packages) {
        if (packSenderThread.getState() == Thread.State.TERMINATED) {
            packSenderThread = new PackSenderThread();
            packSenderThread.setPackages(packages);
        }
        if (packSenderThread.getState() != Thread.State.TIMED_WAITING) {
            packSenderThread.setPackages(packages);
            packSenderThread.start();
        }
    }

    /* public List<Pack> generatePackages() {
        Pack pack1 = new Pack();
        pack1.getParams().add(new Param("a", 3));
        pack1.getParams().add(new Param("b", 10));
        pack1.getParams().add(new Param("c", 10, 25, 5));
        pack1.getParams().add(new Param("s", 0, 0.9, 0.1));

        Pack pack2 = new Pack();
        pack2.getParams().add(new Param("a", 3));
        pack2.getParams().add(new Param("b", 10));
        pack2.getParams().add(new Param("c", 30, 45, 5));
        pack2.getParams().add(new Param("s", 1, 1.9, 0.1));

        Pack pack3 = new Pack();
        pack3.getParams().add(new Param("a", 3));
        pack3.getParams().add(new Param("b", 10));
        pack3.getParams().add(new Param("c", 50, 65, 5));
        pack3.getParams().add(new Param("s", 2, 2.9, 0.1));

        List<Pack> packages = new LinkedList<Pack>();
        packages.add(pack1);
        packages.add(pack2);
        packages.add(pack3);

        return packages;
    }*/

    private class ClientCollectorThread extends Thread {

        @Override
        public void run() {
            try {
                collect();
            } catch (SocketException e) {
                System.out.println("ServerSocket was closed");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void collect() throws IOException, InterruptedException, ClassNotFoundException {
            while (clients.size() < CLIENT_MAX) {
                Socket socket = serverSocket.accept();
                CalculateNode calculateNode = new CalculateNode(socket);
                System.out.println(calculateNode.getMessage());
                clients.add(calculateNode);
            }
        }
    }

    private class PackSenderThread extends Thread {

        private List<Pack> packages;

        public void setPackages(List<Pack> packages) {
            this.packages = packages;
        }

        @Override
        public void run() {
            try {
                sendPackages(packages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendPackages(List<Pack> packages) throws IOException {
            for (Pack pack : packages) {
                CalculateNode calculateNode = getCalculateNode();
                calculateNode.sendMessage(pack);
            }
        }
    }

}

