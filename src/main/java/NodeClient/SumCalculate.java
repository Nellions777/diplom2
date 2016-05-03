package NodeClient;

import student.diplom.web.entities.Param;
import student.diplom.web.server.SetParamWrongException;

import java.util.Map;

/**
 * Created by Андрей on 29.04.2016.
 */
public class SumCalculate extends AbstractCalculate {

    @Override
    public long calculate(Map<Param, Double> setValue) throws SetParamWrongException {
        Double g = setValue.get(new Param("g"));
        Double f = setValue.get(new Param("f"));
        Double d = setValue.get(new Param("d"));
        if (g == null || f == null || d == null) {
            throw new SetParamWrongException();
        } else {
            Double result = calculate(g, f, d);
        }
        long resultId = 0;
        return resultId;
    }

    public Double calculate(double g, double f, double d) {
        return g + f + d;
    }
}
