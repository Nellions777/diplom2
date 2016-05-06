package student.diplom.web.dao;

import org.springframework.stereotype.Repository;
import student.diplom.web.entities.Result;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Евгений on 20.04.2016.
 */

@Repository
public class ResultDao extends JpaCRUD<Result> {

    public ResultDao() {
        super();
        setClazz(Result.class);
    }

    public List<Result> getResultsOnTypeTask(Long typeTaskId){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Result> criteriaQuery = builder.createQuery(Result.class);

        Root<Result> c = criteriaQuery.from(Result.class);
        criteriaQuery.select(c).where(
                builder.equal(c.get("typeTask").get("id").as(Long.class), typeTaskId)
        );
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
