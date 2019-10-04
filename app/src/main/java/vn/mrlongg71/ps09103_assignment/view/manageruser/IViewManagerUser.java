package vn.mrlongg71.ps09103_assignment.view.manageruser;

import java.util.List;

import vn.mrlongg71.ps09103_assignment.model.objectclass.User;

public interface IViewManagerUser {
    void displayListUser(List<User> userList);

    void displayAddUserSuccess();
    void displayAddUserFailed(String message);
}
