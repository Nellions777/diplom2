package NodeClient;

import student.diplom.web.models.Pack;
import student.diplom.web.models.Parameter;

import java.util.List;

public class CalculateIntegral implements Calculable {
    private int DIV_NUMBER = 100;

    @Override
    public void calculate(Pack pack) {
        Parameter a = pack.getParam("a");
        Parameter b = pack.getParam("b");
        Parameter c = pack.getParam("c");
        Parameter s = pack.getParam("s");
        if (a == null || b == null || c == null || s == null) {
            System.out.println("IN Package not found one or more params");
            return;
        }
        calculate(a, b, c, s);
    }

    private void calculate(Parameter a, Parameter b, Parameter c, Parameter s) {
        List<Double> valueListA = a.getListValue();
        List<Double> valueListB = b.getListValue();
        List<Double> valueListC = c.getListValue();
        List<Double> valueListS = s.getListValue();
        for (Double valueA : valueListA) {
            for (Double valueB : valueListB) {
                for (Double valueC : valueListC) {
                    for (Double valueS : valueListS) {
                        calculate(valueA, valueB, valueC, valueS, DIV_NUMBER);
                    }
                }
            }
        }
    }

    public void calculate(double a, double b, double c, double s, int n) {
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
        System.out.println(result);
    }

}
