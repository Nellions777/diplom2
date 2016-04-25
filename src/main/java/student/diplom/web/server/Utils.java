package student.diplom.web.server;

import student.diplom.web.models.Pack;
import student.diplom.web.models.SendSetValue;
import student.diplom.web.models.SetValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Андрей on 14.04.2016.
 */
public class Utils {

    public static List<Pack> divOnPackages2(List<SetValue> params, int countPackages) {
        List<Pack> packages = new LinkedList<>();
        int indexParam = indexDivParam(params, countPackages);
        List<SetValue> divedParams = params.get(indexParam).divParameter(countPackages);
        int part = 0;
        if (indexParam != -1) {
            for (int countPack = 0; countPack < countPackages; countPack++) {
                Pack pack = new Pack();
                for (int i = 0; i < params.size(); i++) {
                    if (i == indexParam) {
                        pack.addParam(divedParams.get(part));
                        part++;
                    } else {
                        pack.addParam(params.get(i));
                    }
                }
                packages.add(pack);
            }
        }
        return packages;
    }

    private static int indexDivParam(List<SetValue> params, int countPackages) {
        for (int i = 0; i < params.size(); i++) {
            if (0 == params.get(i).getCount() % countPackages) {
                return i;
            }
        }
        return -1;
    }

    // когда обрабатываем на сервере
    // list - исходные параметры
    // countItemForPacket - количество значений на один пакет
    public static List<List> divOnPackages1(List<SetValue> list, int countItemForPacket) {

        // индексы по которым проходим
        int i = 0;
        int j = 0;

        // значения на которых останавливаемся
        // мы их сохраняем чтоб потом у ним возвратиться при проходе текущего цикла
        int[] jTemp = new int[list.size()];

        // здесь будет результат
        List<List> allItem = new ArrayList();

        // параметры для нашего пакета
        SendSetValue parameterForPackage;
        List<SendSetValue> packageItem;

        // текущий счётчик
        int count = 0;

        // нижние индексы пакета
        int[] startParams = new int[list.size()];

        // общее количество значений
        int len = 1;
        for (SetValue p : list) {
            len *= p.getCount();
        }

        END: while(true){

            SetValue setValue = list.get(i);
            int countParam = setValue.getCount();

            // возвращаемся назад пока будут другие значения
            while(j >= countParam){
                if(i!=0) {
                    j = jTemp[--i];
                    setValue = list.get(i);
                    countParam = setValue.getCount();
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

                            parameterForPackage = new SendSetValue();
                            setValue = list.get(k);
                            parameterForPackage.setParam(setValue.getParam());
                            parameterForPackage.setStep(setValue.getStep());

                            if (setValue.getCount() != 1) {
                                parameterForPackage.setStart(setValue.getStart());
                                parameterForPackage.setBeginIndex(startParams[k]);

                                if(k != list.size()-1) {
                                    parameterForPackage.setEnd(setValue.getEnd());
                                    parameterForPackage.setEndIndex(jTemp[k] - 1);

                                    // заменяем начальное значение пакетов
                                    startParams[k] = jTemp[k]-1;
                                }
                                else {
                                    parameterForPackage.setEnd(setValue.getEnd());
                                    parameterForPackage.setEndIndex(j);

                                    // заменяем начальное значение пакетов
                                    startParams[k] = j+1;
                                }
                            }else {
                                parameterForPackage.setValue(setValue.getValue());
                            }
                            packageItem.add(parameterForPackage);

                        }
                        allItem.add(packageItem);

                    }else if(len - allItem.size()*countItemForPacket <= countItemForPacket){
                        // идём по последнему пакету
                        for(int k=0; k<startParams.length; k++){

                            parameterForPackage = new SendSetValue();
                            setValue = list.get(k);
                            parameterForPackage.setParam(setValue.getParam());
                            parameterForPackage.setStep(setValue.getStep());

                            if (setValue.getCount() != 1) {
                                parameterForPackage.setStart(setValue.getStart());
                                parameterForPackage.setEnd(setValue.getEnd());

                                if(k != list.size()-1) {
                                    parameterForPackage.setBeginIndex(jTemp[k]-1);
                                }
                                else {
                                    parameterForPackage.setBeginIndex(j);
                                }
                                parameterForPackage.setEndIndex(setValue.getCount() - 1);
                            }else {
                                parameterForPackage.setValue(setValue.getValue());
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
