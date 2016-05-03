package student.diplom.web.server;

import student.diplom.web.entities.Param;
import student.diplom.web.models.IterateParam;
import student.diplom.web.models.Pack;
import student.diplom.web.models.SimpleParam;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by Андрей on 14.04.2016.
 */
public class Utils {

    private static List<Pack> packages;

    public static List<Pack> divOnPackages(Map<Param, IterateParam> paramMap, List<Param> divParams) {

        packages = new LinkedList<>();

        List<Param> keyList = new LinkedList<>(paramMap.keySet());
        ListIterator<Param> keyListIterator = keyList.listIterator();
        Pack pack = new Pack();
        divider(paramMap, keyListIterator, divParams, pack);

        return packages;
    }

    private static void divider(Map<Param, IterateParam> paramMap, ListIterator<Param> keyListIterator,
                                List<Param> divParam, Pack pack) {
        if (keyListIterator.hasNext()) {
            Param currentParam = keyListIterator.next();
            if (divParam.contains(currentParam)) {
                IterateParam ip = paramMap.get(currentParam);
                while (ip.hasNext()) {
                    SimpleParam sp = new SimpleParam(ip.getParam(), ip.next());
                    Pack newPack = new Pack();
                    newPack.getSetValues().addAll(pack.getSetValues());
                    newPack.addParam(sp);
                    divider(paramMap, keyListIterator, divParam, newPack);
                }
                while (paramMap.get(currentParam).hasPrevious()) {
                    paramMap.get(currentParam).previous();
                }
            } else {
                pack.addParam(paramMap.get(currentParam));
                divider(paramMap, keyListIterator, divParam, pack);
            }
            keyListIterator.previous();
        } else {
            packages.add(pack);
        }

    }
}
