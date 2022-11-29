package lk.ijse.dep9.service.custome;

import lk.ijse.dep9.dto.UserDTO;
import lk.ijse.dep9.service.SuperService;
import lk.ijse.dep9.service.exception.DuplicateException;
import lk.ijse.dep9.service.exception.NotFoundException;

public interface UserService extends SuperService {
    void signupMember(UserDTO user) throws DuplicateException;

    void removeUserAccount(String user_name) throws NotFoundException;

    UserDTO getUserDetails(String user_name) throws NotFoundException;



}
