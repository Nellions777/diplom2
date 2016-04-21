package student.diplom.web.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Parameter implements Serializable{

    private static final long serialVersionUID = 1L;
    private String name = null;
    private double value;
    private double start;
    private double end;
    private double step;

    public Parameter(){

    }

    public Parameter(String name, double start, double end, double step){
        this.name = name;
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public Parameter(String name, double value){
        this.name = name;
        this.value = value;
    }

    public Parameter(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        step = 0;
        this.value = value;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public List<Parameter> divParameter(int count) {
        List<Parameter> params = new LinkedList<>();
        double totalStep = (this.getEnd() - this.getStart() + step) / count;
        if (totalStep == this.step) {
            for (int i = 0; i < count; i++) {
                params.add(new Parameter(this.name, this.start + i * totalStep));
            }
        } else {
            for (int i = 0; i < count; i++) {
                params.add(new Parameter(this.name, this.start + i * totalStep, this.start + (i + 1) * totalStep - this.step, this.step));
            }
        }
        return params;
    }

    public List<Double> getListValue() {
        List<Double> valueList = new LinkedList<>();
        double value = this.start;
        if (step == 0) {
            valueList.add(value);
        } else {
            while (value <= this.end) {
                valueList.add(value);
                value += step;
            }
        }
        return valueList;
    }

    // количество значений в параметре
    public int getCount(){
        int count = 0;
        if(step != 0){
            count = (int)((end-start)/step+1);
        }else count++;
        return count;
    }
}
