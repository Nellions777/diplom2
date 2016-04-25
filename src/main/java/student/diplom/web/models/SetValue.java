package student.diplom.web.models;

import student.diplom.web.entities.Param;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SetValue implements Serializable {

    private static final long serialVersionUID = 1L;
    private Param param = null;
    private double value;
    private double start;
    private double end;
    private double step;

    public SetValue() {

    }

    public SetValue(Param param, double start, double end, double step) {
        this.param = param;
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public SetValue(Param param, double value) {
        this.param = param;
        this.value = value;
    }

    public SetValue(Param param) {
        this.param = param;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
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

    public List<SetValue> divParameter(int count) {
        List<SetValue> params = new LinkedList<>();
        double totalStep = (this.getEnd() - this.getStart() + step) / count;
        if (totalStep == this.step) {
            for (int i = 0; i < count; i++) {
                params.add(new SetValue(this.param, this.start + i * totalStep));
            }
        } else {
            for (int i = 0; i < count; i++) {
                params.add(new SetValue(this.param, this.start + i * totalStep, this.start + (i + 1) * totalStep - this.step, this.step));
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
