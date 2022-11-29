package lk.ijse.dep9.dto;

import jakarta.validation.constraints.NotNull;

public class UserDTO {
    @NotNull(message = "User Name can't be empty")
    private String userName;
    @NotNull (message = "Full name can't be empty")
    private String fullName;
    @NotNull(message = "Password can't be empty")
    private String password;

    public UserDTO() {
    }

    public UserDTO(String userName, String fullName, String password) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
