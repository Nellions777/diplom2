package student.diplom.web.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 11.04.2016.
 */
@Entity
public class TypeTask implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "typeTask", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Param> params = new ArrayList<>();

    public TypeTask(){

    }

    public TypeTask(String name){
        this.name = name;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
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

    @Override
    public String toString() {
        return "TypeTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
