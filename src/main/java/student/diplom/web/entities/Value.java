package student.diplom.web.entities;

import javax.persistence.*;

/**
 * Created by Евгений on 11.04.2016.
 */

@Entity
public class Value {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Result result;

    @OneToOne
    private Param param;

    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
