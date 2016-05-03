package NodeClient;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

/**
 * Created by Евгений on 16.03.2016.
 */
public class ServiceSocket {

    public static void main(String[] arg){
        int serverPort = 6666;
        String address = "127.0.0.1";

        ServiceSocket serviceSocket = new ServiceSocket();
        List<Double> results;

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);
            System.out.println("Connected");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);
            ObjectOutputStream out = new ObjectOutputStream(outputStream);

            List packet = (List)in.readObject();
            //results = serviceSocket.workingWithPacket(packet);
            //out.writeObject(results);

            in.close();
            out.close();
            inputStream.close();
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*public static double methodSumm(double[] params){
        double result = 0;
        for(double item: params){
            result += item;
        }
        return result;
    }

    // когда отдаёт клиенту
    // packet - параметры, с которых считаем результат
    /*public static List<Double> workingWithPacket(List<SendSetValue> packet){

        SendSetValue sendParameter;
        int countParam;

        // значения на которых останавливаемся
        // мы их сохраняем чтоб потом у ним возвратиться при проходе текущего цикла
        int[] jTemp = new int[packet.size()];
        for(int k=0; k<packet.size(); k++){
            jTemp[k] = packet.get(k).getBeginIndex()+1; // +1 так как сохраняем след шаг
        }

        // номер параметра
        int i = packet.size()-1;
        // номер строчки
        int j = jTemp[packet.size()-1]-1;

        // строка для вывода на экран
        String output = new String();

        // параметры для функции для вычисления
        double[] params = new double[packet.size()];

        List<Double> results = new ArrayList();

        // идём по параметрам
        END: while(true){

            sendParameter = packet.get(i);
            countParam = sendParameter.getCount();

            // возвращаемся назад пока будут другие значения
            while(j >= countParam){
                if(i!=0) {
                    j = jTemp[--i];
                    sendParameter = packet.get(i);
                    countParam = sendParameter.getCount();
                }
            }
            while(j<countParam){

                if(i != packet.size()-1){ // промежуточные параметры
                    // когда идём вниз, сохраняем(следущую позицию) и сбрасываем счётчик
                    jTemp[i] = ++j;
                    j=0;
                    i++;
                    break;
                }else{
                    // идём по последнему параметру и возвращаемся к предыдущей итерации
                    // выводим записи с листа по порядку
                    for(int k=0; k<packet.size(); k++){
                        sendParameter = packet.get(k);
                        countParam = sendParameter.getCount();

                        if(countParam == 1) {
                            output+= sendParameter.getValue() + " ";
                            params[k] = sendParameter.getValue();
                        }else {
                            if(k != packet.size()-1) {
                                output+= sendParameter.getStart()+(jTemp[k]-1)*sendParameter.getStep() + " ";
                                params[k] = sendParameter.getStart()+(jTemp[k]-1)*sendParameter.getStep();
                            }else {
                                output+= sendParameter.getStart()+j*sendParameter.getStep() + " ";
                                params[k] = sendParameter.getStart()+j*sendParameter.getStep();
                            }
                        }
                    }
                    results.add(methodSumm(params));
                    System.out.println(output);
                    output="";

                    boolean isEnd = true;
                    for(int k=0; k<packet.size(); k++){
                        if(k == packet.size()-1){
                            if(j != packet.get(k).getEndIndex()) {
                                isEnd = false;
                                break;
                            }
                        }else if(jTemp[k]-1 != packet.get(k).getEndIndex()) {
                            isEnd = false;
                            break;
                        }
                    }
                    if(isEnd) break END;

                    // идём на предыдущий шаг
                    // if(j == countParam-1){
                    //   j = jTemp[--i];
                    // break;
                    //}
                }
                j++;
            }
        }
        return results;
    }*/
}
