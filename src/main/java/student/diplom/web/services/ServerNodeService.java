package student.diplom.web.services;

import org.springframework.stereotype.Service;
import student.diplom.web.models.Parameter;
import student.diplom.web.models.SendParameter;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 16.03.2016.
 */

@Service
public class ServerNodeService {

    public List<Double> createServerSocket(List<Parameter> list){

        int port = 6666;

        List<List> sendPackets = divOnPackets(list, 10); // [2;inf]
        List<Double> results = new ArrayList();

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for a client...");


            for(List<SendParameter> packet : sendPackets){
                Socket socket = serverSocket.accept();
                System.out.println("Got a client");

                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
                ObjectOutputStream out = new ObjectOutputStream(outputStream);
                ObjectInputStream in = new ObjectInputStream(inputStream);

                out.writeObject(packet);
                out.flush();

                results.addAll((List<Double>)in.readObject());

                out.close();
                outputStream.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("========="+results);
        return results;
    }

    // когда обрабатываем на сервере
    // list - исходные параметры
    // countItemForPacket - количество значений на один пакет
    private List<List> divOnPackets(List<Parameter> list, int countItemForPacket){

        // индексы по которым проходим
        int i = 0;
        int j = 0;

        // значения на которых останавливаемся
        // мы их сохраняем чтоб потом у ним возвратиться при проходе текущего цикла
        int[] jTemp = new int[list.size()];

        // здесь будет результат
        List<List> allItem = new ArrayList();

        // параметры для нашего пакета
        SendParameter parameterForPackage;
        List<SendParameter> packageItem;

        // текущий счётчик
        int count = 0;

        // нижние индексы пакета
        int[] startParams = new int[list.size()];

        // общее количество значений
        int len = 1;
        for (Parameter p : list){
            len *= p.getCount();
        }

        END: while(true){

            Parameter parameter = list.get(i);
            int countParam = parameter.getCount();

            // возвращаемся назад пока будут другие значения
            while(j >= countParam){
                if(i!=0) {
                    j = jTemp[--i];
                    parameter = list.get(i);
                    countParam = parameter.getCount();
                }
            }
            while(j<countParam){

                if(i != list.size()-1){ // промежуточные параметры
                    // когда идём вперёд сохраняем(следущую позицию) и сбрасывает счётчик
                    jTemp[i] = ++j;
                    j=0;
                    i++;
                    break;
                }else{
                    // идём по последнему параметру и возвращаемся к предыдущей итерации

                    packageItem = new ArrayList();
                    // если достигли нужное количество пакетов
                    if(++count%countItemForPacket == 0){
                        //System.out.println("--------------");
                        // деление на пакеты(от 0 до jTemp[i]-1)
                        // сформировать json обекты для каждого пакета
                        for(int k=0; k<startParams.length; k++){

                            parameterForPackage = new SendParameter();
                            parameter = list.get(k);
                            parameterForPackage.setName(parameter.getName());
                            parameterForPackage.setStep(parameter.getStep());
                            parameterForPackage.setIsOneValue(parameter.getIsOneValue());

                            if(parameter.getCount() != 1) {
                                parameterForPackage.setStart(parameter.getStart());
                                parameterForPackage.setBeginIndex(startParams[k]);

                                if(k != list.size()-1) {
                                    parameterForPackage.setEnd(parameter.getEnd());
                                    parameterForPackage.setEndIndex(jTemp[k] - 1);

                                    // заменяем начальное значение пакетов
                                    startParams[k] = jTemp[k]-1;
                                }
                                else {
                                    parameterForPackage.setEnd(parameter.getEnd());
                                    parameterForPackage.setEndIndex(j);

                                    // заменяем начальное значение пакетов
                                    startParams[k] = j+1;
                                }
                            }else {
                                parameterForPackage.setValue(parameter.getValue());
                            }
                            packageItem.add(parameterForPackage);

                        }
                        allItem.add(packageItem);

                    }else if(len - allItem.size()*countItemForPacket <= countItemForPacket){
                        // идём по последнему пакету
                        for(int k=0; k<startParams.length; k++){

                            parameterForPackage = new SendParameter();
                            parameter = list.get(k);
                            parameterForPackage.setName(parameter.getName());
                            parameterForPackage.setStep(parameter.getStep());
                            parameterForPackage.setIsOneValue(parameter.getIsOneValue());

                            if(parameter.getCount() != 1) {
                                parameterForPackage.setStart(parameter.getStart());
                                parameterForPackage.setEnd(parameter.getEnd());

                                if(k != list.size()-1) {
                                    parameterForPackage.setBeginIndex(jTemp[k]-1);
                                }
                                else {
                                    parameterForPackage.setBeginIndex(j);
                                }
                                parameterForPackage.setEndIndex(parameter.getCount()-1);
                            }else {
                                parameterForPackage.setValue(parameter.getValue());
                            }
                            packageItem.add(parameterForPackage);
                        }
                        allItem.add(packageItem);
                        // выходим
                        break END;
                    }

                    /*if(j == countParam-1){
                        j = jTemp[--i];
                        break;
                    }*/
                }
                j++;
            }
        }
        return allItem;
    }
}
