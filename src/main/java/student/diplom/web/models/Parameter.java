package student.diplom.web.models;

import java.io.Serializable;

/**
 * Created by Евгений on 25.02.2016.
 */
public class Parameter implements Serializable{

    private static final long serialVersionUID = 1L;
    private String name = null;
    private double value;
    private double start;
    private double end;
    private double step;

    private boolean isOneValue;

    public Parameter(){

    }

    public Parameter(String name, double start, double end, double step){
        this.name = name;
        this.start = start;
        this.end = end;
        this.step = step;
        this.isOneValue = false;
    }

    public Parameter(String name, double value){
        this.name = name;
        this.value = value;
        this.isOneValue = true;
    }

    public Parameter(String name, boolean isOneValue){
        this.name = name;
        this.isOneValue = isOneValue;
    }

    public boolean getIsOneValue() {
        return isOneValue;
    }

    public void setIsOneValue(boolean isOneValue) {
        this.isOneValue = isOneValue;
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

    // количество значений в параметре
    public int getCount(){
        int count = 0;
        if(!isOneValue){
            count = (int)((end-start)/step+1);
        }else count++;
        return count;
    }
}
