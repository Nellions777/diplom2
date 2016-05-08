package student.diplom.web.models;

import student.diplom.web.entities.TypeTask;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Pack implements Serializable {

    private static final long serialVersionUID = 1L;

    private TypeTask typeTask;

    private List<IterateParam> setValues = new LinkedList<IterateParam>();

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public List<IterateParam> getSetValues() {
        return setValues;
    }

    public void setSetValues(List<IterateParam> setValues) {
        this.setValues = setValues;
    }

    public void addParam(IterateParam setValue) {
        setValues.add(setValue);
    }

    public IterateParam getSetValueByName(String name) {
        for (IterateParam setValue : setValues) {
            if (name.equals(setValue.getParam().getName())) {
                return setValue;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = new String();
        for (IterateParam setValue : setValues) {
            str += setValue.toString();
        }
        return str;
    }
}
