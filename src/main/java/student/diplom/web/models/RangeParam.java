package student.diplom.web.models;

import student.diplom.web.entities.Param;

import java.util.NoSuchElementException;

/**
 * Created by Андрей on 28.04.2016.
 */
public class RangeParam extends IterateParam {

    private Double start;
    private Double finish;
    private Double step;
    private int counter = 0;

    public RangeParam(){}

    public RangeParam(Param param, Double start, Double finish, Double step) {
        this.param = param;
        this.start = start;
        this.finish = finish;
        this.step = step;
    }

    public Double getStart() {
        return start;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public Double getFinish() {
        return finish;
    }

    public void setFinish(Double finish) {
        this.finish = finish;
    }

    public Double getStep() {
        return step;
    }

    public void setStep(Double step) {
        this.step = step;
    }

    @Override
    public boolean hasNext() {
        return (start + counter * step) <= finish;
    }

    @Override
    public Double next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return start + step * counter++;
    }

    @Override
    public boolean hasPrevious() {
        return (start + counter * step) > start;
    }

    @Override
    public Double previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        return start + step * counter--;
    }

    @Override
    public int nextIndex() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return counter + 1;
    }

    @Override
    public int previousIndex() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        return counter - 1;
    }

    @Override
    public void remove() {

    }

    @Override
    public void set(Double aDouble) {

    }

    @Override
    public void add(Double aDouble) {

    }

    @Override
    public String toString() {
        return  super.param.getName() +": " + start + " ... " + finish + ", " + step;
    }
}
