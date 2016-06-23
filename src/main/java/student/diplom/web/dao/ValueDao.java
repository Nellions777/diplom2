package student.diplom.web.dao;

import org.springframework.stereotype.Repository;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.Result;
import student.diplom.web.entities.Value;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

/**
 * Created by Евгений on 20.04.2016.
 */

@Repository
public class ValueDao extends JpaCRUD<Value> {

    public ValueDao() {
        super();
        setClazz(Value.class);
    }

    public List<Value> valuesOnParam(Param p){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Value> criteriaQuery = builder.createQuery(Value.class);

        Root<Value> c = criteriaQuery.from(Value.class);
        criteriaQuery.select(c).where(
                builder.equal(c.get("param").as(Param.class), p)
        );
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    public Value valueOnParamAndResult(Param p,Result r){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Value> criteriaQuery = builder.createQuery(Value.class);

        Root<Value> c = criteriaQuery.from(Value.class);
        criteriaQuery.select(c).where(
                builder.and(builder.equal(c.get("param").as(Param.class), p), builder.equal(c.get("result").as(Result.class), r))
        );
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
