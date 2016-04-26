package student.diplom.web.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Евгений on 11.04.2016.
 */

@Entity
public class Param implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private TypeTask typeTask;

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

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public Boolean getIsInput() {
        return isInput;
    }

    public void setIsInput(Boolean isInput) {
        this.isInput = isInput;
    }
}
