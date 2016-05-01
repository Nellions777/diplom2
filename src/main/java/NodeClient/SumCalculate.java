package NodeClient;

import student.diplom.web.entities.Param;

import java.util.Map;

/**
 * Created by Андрей on 29.04.2016.
 */
public class SumCalculate extends AbstractCalculate {

    @Override
    public void calculate(Map<Param, Double> setValue) {
        Double result = calculate(setValue.get(new Param("g")), setValue.get(new Param("f")), setValue.get(new Param("d")));
    }

    public Double calculate(double a, double b, double c) {
        return a + b + c;
    }
}
