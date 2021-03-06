package student.diplom.web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Евгений on 11.04.2016.
 */

@Entity
public class Result {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private TypeTask typeTask;

    private Long timeTask;

    private Integer client;

    @JsonIgnore
    @OneToMany(mappedBy = "result", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Value> values;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public Long getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(Long timeTask) {
        this.timeTask = timeTask;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
