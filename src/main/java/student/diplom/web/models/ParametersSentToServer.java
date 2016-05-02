package student.diplom.web.models;

import student.diplom.web.entities.Param;

import java.util.List;

/**
 * Created by Евгений on 02.05.2016.
 */
public class ParametersSentToServer {
    List<SimpleParam> simpleParams;
    List<RangeParam> rangeParams;
    List<Long> paramsIdForDivOnPackages;

    ParametersSentToServer(){

    }

    public List<SimpleParam> getSimpleParams() {
        return simpleParams;
    }

    public void setSimpleParams(List<SimpleParam> simpleParams) {
        this.simpleParams = simpleParams;
    }

    public List<RangeParam> getRangeParams() {
        return rangeParams;
    }

    public void setRangeParams(List<RangeParam> rangeParams) {
        this.rangeParams = rangeParams;
    }

    public List<Long> getParamsIdForDivOnPackages() {
        return paramsIdForDivOnPackages;
    }

    public void setParamsIdForDivOnPackages(List<Long> paramsIdForDivOnPackages) {
        this.paramsIdForDivOnPackages = paramsIdForDivOnPackages;
    }
}
