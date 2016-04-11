package student.diplom.web.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by Евгений on 11.04.2016.
 */

@Entity
public class Param {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne
    private TypeTask task;

    private Boolean isInput;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeTask getTask() {
        return task;
    }

    public void setTask(TypeTask task) {
        this.task = task;
    }

    public Boolean getIsInput() {
        return isInput;
    }

    public void setIsInput(Boolean isInput) {
        this.isInput = isInput;
    }
}
