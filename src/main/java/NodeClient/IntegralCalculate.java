package NodeClient;

import student.diplom.web.entities.Param;

import java.util.Map;

/**
 * Created by Андрей on 29.04.2016.
 */
public class IntegralCalculate extends AbstractCalculate {

    private int DIV_NUMBER = 100;

    @Override
    public void calculate(Map<Param, Double> setValue) {
        Double result = calculate(setValue.get(new Param("a")), setValue.get(new Param("b")), setValue.get(new Param("c")), setValue.get(new Param("s")), DIV_NUMBER);
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
