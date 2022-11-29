package lk.ijse.dep9.service;

import lk.ijse.dep9.service.custome.UserService;
import lk.ijse.dep9.service.custome.impl.ToDoItemServiceImpl;
import lk.ijse.dep9.service.custome.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return (serviceFactory == null) ? (serviceFactory = new ServiceFactory()): serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceTypes serviceTypes){
        final SuperService service;
        switch (serviceTypes){
            case USER:
                service= new UserServiceImpl() {
                };
                break;
            case TO_DO_ITEM:
                service =  new ToDoItemServiceImpl();
                break;

            default:
                service = null;
        }
        return (T) service;
    }
}

