package student.diplom.web.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Pack implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Parameter> params = new LinkedList<Parameter>();

    public List<Parameter> getParams() {
        return params;
    }

    public void setParams(List<Parameter> params) {
        this.params = params;
    }

    public void addParam(Parameter param) {
        params.add(param);
    }

    public Parameter getParam(String name) {
        for (Parameter param : params) {
            if (param.getName().equals(name)) {
                return param;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = new String();
        for (Parameter param : params) {
            if (param.getStep() == 0) {
                str += param.getName() + " : value = " + param.getValue() + "\n";
            } else {
                str += param.getName() + " : min = " + param.getStart() + ", max = " + param.getEnd() + "; step = "
                        + param.getStep() + "\n";
            }

        }
        return str;
    }
}
