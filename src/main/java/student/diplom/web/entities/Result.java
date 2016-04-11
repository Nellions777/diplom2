package student.diplom.web.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
}
