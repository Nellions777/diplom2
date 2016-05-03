package NodeClient;

import student.diplom.web.entities.Param;
import student.diplom.web.server.SetParamWrongException;

import java.util.Map;

/**
 * Created by Андрей on 29.04.2016.
 */
public class IntegralCalculate extends AbstractCalculate {

    private int DIV_NUMBER = 100;

    @Override
    public long calculate(Map<Param, Double> setValue) throws SetParamWrongException {
        Double a = setValue.get(new Param("a"));
        Double b = setValue.get(new Param("b"));
        Double c = setValue.get(new Param("c"));
        Double s = setValue.get(new Param("s"));
        if (a == null || b == null || c == null || s == null) {
            throw new SetParamWrongException();
        } else {
            Double result = calculate(a, b, c, s, DIV_NUMBER);
        }
        long resultId = 0;
        return resultId;
    }

    public Double calculate(double a, double b, double c, double s, int n) {
        double d = (b - a) / n;
        double result = 0;
        double xm;
        double ym;
        for (int i = 0; i < n; i++) {
            xm = a + i * d + d / 2;
            // ym = d * xm * xm;
            ym = c * Math.sin(Math.pow(Math.E, s * xm));
            result += d * ym;
        }
        return result;
    }
}
