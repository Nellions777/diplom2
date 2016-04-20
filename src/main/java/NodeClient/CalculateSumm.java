package NodeClient;

import student.diplom.web.models.Pack;
import student.diplom.web.models.Parameter;

import java.util.List;

public class CalculateSumm implements Calculable {

    @Override
    public void calculate(Pack pack) {
        calculate(pack.getParam("a"), pack.getParam("b"), pack.getParam("c"));
    }

    private void calculate(Parameter a, Parameter b, Parameter c) {
        List<Double> valueListA = a.getListValue();
        List<Double> valueListB = b.getListValue();
        List<Double> valueListC = c.getListValue();
        for (Double valueA : valueListA) {
            for (Double valueB : valueListB) {
                for (Double valueC : valueListC) {
                    calculate(valueA, valueB, valueC);
                }
            }
        }
    }

    public void calculate(double a, double b, double c) {
        double result = a + b + c;
        System.out.println(result);
    }

}
