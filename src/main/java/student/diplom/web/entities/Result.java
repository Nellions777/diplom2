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
    private TypeTask task;

    private Long timeTask;

    private Integer client;

    @JsonIgnore
    @OneToMany(mappedBy = "result", fetch = FetchType.EAGER)
    private List<Value> values;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTask getTask() {
        return task;
    }

    public void setTask(TypeTask task) {
        this.task = task;
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
