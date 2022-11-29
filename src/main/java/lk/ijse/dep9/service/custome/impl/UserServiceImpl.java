package lk.ijse.dep9.service.custome.impl;

import lk.ijse.dep9.dto.UserDTO;
import lk.ijse.dep9.service.custome.UserService;
import lk.ijse.dep9.service.exception.DuplicateException;
import lk.ijse.dep9.service.exception.NotFoundException;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public void signupMember(UserDTO user) throws DuplicateException {

    }

    @Override
    public void removeUserAccount(String user_name) throws NotFoundException {

    }

    @Override
    public UserDTO getUserDetails(String user_name) throws NotFoundException {
        return null;
    }
}
