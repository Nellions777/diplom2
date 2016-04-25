package student.diplom.web.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Евгений on 11.04.2016.
 */
@Entity
public class TypeTask implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public TypeTask(){

    }

    public TypeTask(String name){
        this.name = name;
    }

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
}
