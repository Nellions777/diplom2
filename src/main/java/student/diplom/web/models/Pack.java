package student.diplom.web.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Pack implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<SetValue> setValues = new LinkedList<SetValue>();

    public List<SetValue> getSetValues() {
        return setValues;
    }

    public void setSetValues(List<SetValue> setValues) {
        this.setValues = setValues;
    }

    public void addParam(SetValue setValue) {
        setValues.add(setValue);
    }

    public SetValue getSetValueByName(String name) {
        for (SetValue setValue : setValues) {
            if (name.equals(setValue.getParam().getName())) {
                return setValue;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = new String();
        for (SetValue setValue : setValues) {
            if (setValue.getStep() == 0) {
                str += setValue.getParam().getName() + " : value = " + setValue.getValue() + "\n";
            } else {
                str += setValue.getParam().getName() + " : min = " + setValue.getStart() + ", max = " + setValue.getEnd() + "; step = "
                        + setValue.getStep() + "\n";
            }

        }
        return str;
    }
}
