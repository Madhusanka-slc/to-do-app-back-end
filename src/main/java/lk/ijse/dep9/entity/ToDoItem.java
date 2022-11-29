package lk.ijse.dep9.entity;


import lk.ijse.dep9.entity.util.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoItem implements SuperEntity {
    private int id;
    private String username;
    private String description;
    private StatusType status;
}
