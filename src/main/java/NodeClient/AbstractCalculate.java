package NodeClient;

import student.diplom.web.entities.Param;

import java.util.*;

/**
 * Created by Андрей on 29.04.2016.
 */
public abstract class AbstractCalculate {
    private Map<Param, ListIterator<Double>> paramMap;

    public void init(Map<Param, ListIterator<Double>> paramMap) {
        this.paramMap = paramMap;
        List<Param> keyList = new LinkedList<Param>(paramMap.keySet());
        ListIterator<Param> keyListIterator = keyList.listIterator();
        Map<Param, Double> mySetValue = new HashMap<>();
        calculate(keyListIterator, mySetValue);
    }

    private void calculate(ListIterator<Param> keyListIterator, Map<Param, Double> setValue) {
        if (keyListIterator.hasNext()) {
            Param currentParam = keyListIterator.next();
            while (paramMap.get(currentParam).hasNext()) {
                setValue.put(currentParam, paramMap.get(currentParam).next());
                calculate(keyListIterator, setValue);
            }
            while (paramMap.get(currentParam).hasPrevious()) {
                paramMap.get(currentParam).previous();
            }
            keyListIterator.previous();
        } else {
            long startTime = System.currentTimeMillis();
            calculate(setValue);
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println(setValue);
        }
    }

    protected abstract void calculate(Map<Param, Double> setValue);


}
