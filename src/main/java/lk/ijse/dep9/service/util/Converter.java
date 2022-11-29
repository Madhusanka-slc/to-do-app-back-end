package lk.ijse.dep9.service.util;

import lk.ijse.dep9.dto.UserDTO;
import lk.ijse.dep9.entity.User;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.time.LocalDate;

public class Converter {

    private ModelMapper mapper;

    public Converter() {
        this.mapper = new ModelMapper();
    }

    public User toUser(UserDTO userDTO){
        return mapper.map(userDTO, User.class);
    }

    public UserDTO toUserDTO(User userEntity){
        return mapper.map(userEntity, UserDTO.class);
    }

}
