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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public ToDoItem(String username, String description, StatusType status) {
        this.username = username;
        this.description = description;
        this.status = status;
    }
}
