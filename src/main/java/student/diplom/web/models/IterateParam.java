package student.diplom.web.models;

import student.diplom.web.entities.Param;

import java.io.Serializable;
import java.util.ListIterator;

/**
 * Created by Андрей on 28.04.2016.
 */
public abstract class IterateParam implements ListIterator<Double>, Serializable {
    protected Param param;

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

}
