package student.diplom.web.models;

import student.diplom.web.entities.Param;

import java.util.NoSuchElementException;

/**
 * Created by Андрей on 28.04.2016.
 */
public class SimpleParam extends IterateParam {
    private Double value;

    private int currentIndex = 0;

    public SimpleParam(){}

    public SimpleParam(Param param, Double value) {
        this.param = param;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < 1;
    }

    @Override
    public Double next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentIndex++;
        return value;
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    @Override
    public Double previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        currentIndex--;
        return value;
    }

    @Override
    public int nextIndex() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return 0;
    }

    @Override
    public int previousIndex() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        return 0;
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
        return super.param.getName() +
                ": " + value;
    }
}
