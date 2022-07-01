package jp.kobespiral.shoda.todo.repository;//変える
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import jp.kobespiral.shoda.todo.entity.ToDo;//変える
@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long>{

    List<ToDo> findAll();

    List<ToDo> findByMidAndDone(String mid, boolean done);

    List<ToDo> findByDone(boolean done);

}