package jp.kobespiral.shoda.todo.dto;

import java.util.Date;

import jp.kobespiral.shoda.todo.entity.ToDo;
import lombok.Data;
@Data
public class ToDoForm {
    String title;

    public ToDo toEntity() {
        ToDo t = new ToDo(null, title, null, false, new Date(), null);
        return t;
    }
}