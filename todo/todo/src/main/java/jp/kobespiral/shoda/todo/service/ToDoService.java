package jp.kobespiral.shoda.todo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import jp.kobespiral.shoda.todo.dto.ToDoForm;
import jp.kobespiral.shoda.todo.entity.ToDo;
import jp.kobespiral.shoda.todo.exception.ToDoAppException;
import jp.kobespiral.shoda.todo.repository.ToDoRepository;

@Service
public class ToDoService {
   @Autowired
   ToDoRepository tRepo;

    public ToDo createToDo(String mid, ToDoForm form) {
        /* メンバーmidが新しくToDoを作成する */
        ToDo t = form.toEntity();
        t.setMid(mid);
        return tRepo.save(t);
    }

    public ToDo getToDo(Long seq) {
        ToDo t = tRepo.findById(seq).orElseThrow(
               () -> new ToDoAppException(ToDoAppException.NO_SUCH_SEQ_EXISTS, seq + ": No such seq exists"));
        return t;
    }

    public List<ToDo> getToDoList(String mid) {
        /* midのToDoリストを取得 */
        return tRepo.findByMidAndDone(mid, false);
    }

    public List<ToDo> getDoneList(String mid) {
        /* midのDoneリストを取得 */
        return tRepo.findByMidAndDone(mid, true);
    }

    public List<ToDo> getToDoList() {
        /* 全員のToDoリストを取得 */
        return tRepo.findByDone(false);
    }

    public List<ToDo> getDoneList() {
        /* 全員のDoneリストを取得 */
        return tRepo.findByDone(true);
    }

    public void done(Long seq){
        ToDo t = getToDo(seq);
        t.setDone(true);
        t.setDoneAt(new Date());
        tRepo.save(t);
    }

    public void deleteToDo(Long seq){
        tRepo.deleteById(seq);
    }
   
}