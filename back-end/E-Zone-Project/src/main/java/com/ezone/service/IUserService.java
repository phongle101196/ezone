package com.ezone.service;

import com.ezone.entity.User;
import com.ezone.form.create.CreatingUserForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingUserForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    Page<User> getAllUser(Pageable pageable, FilterForm form);

    User getUserById(int userId);

    void createNewUser(CreatingUserForm form);

    void updateUser(UpdatingUserForm form);

    void deleteUserById(int userId);

    void setUserAdmin(int userId);

    void setUserMod(int userId);

    void setUserManager(int userId);

    void setUserStaff(int userId);

    void setUserMember(int userId);

    void activeUser(int userId);
}
