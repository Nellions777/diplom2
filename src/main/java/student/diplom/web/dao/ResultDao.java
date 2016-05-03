package student.diplom.web.dao;

import org.springframework.stereotype.Repository;
import student.diplom.web.entities.Result;

/**
 * Created by Евгений on 20.04.2016.
 */

@Repository
public class ResultDao extends JpaCRUD<Result> {

    public ResultDao() {
        super();
        setClazz(Result.class);
    }
}
