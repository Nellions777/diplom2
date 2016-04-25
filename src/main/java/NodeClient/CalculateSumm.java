package NodeClient;

import student.diplom.web.models.Pack;
import student.diplom.web.models.SetValue;

import java.util.List;

public class CalculateSumm implements Calculable {

    @Override
    public void calculate(Pack pack) {
        calculate(pack.getSetValueByName("a"), pack.getSetValueByName("b"), pack.getSetValueByName("c"));
    }

    private void calculate(SetValue a, SetValue b, SetValue c) {
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
