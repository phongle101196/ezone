package com.ezone.controller;

import com.ezone.dto.UserDTO;
import com.ezone.entity.User;
import com.ezone.form.create.CreatingUserForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingUserForm;
import com.ezone.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<UserDTO> getAllUser(Pageable pageable, FilterForm form) {
        Page<User> userPage = userService.getAllUser(pageable, form);
        List<User> users = userPage.getContent();
        List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>() {
        }.getType());
        return new PageImpl<>(userDTOS, pageable, userPage.getTotalElements());
    }

    @GetMapping(value = "/{userId}")
    public UserDTO getUserById(@PathVariable(name = "userId") int userId) {
        return modelMapper.map(userService.getUserById(userId), UserDTO.class);
    }

    @PostMapping
    public void createNewUser(@RequestBody CreatingUserForm form) {
        userService.createNewUser(form);
    }

    @PutMapping(value = "/{userId}")
    public void updateUser(@PathVariable(name = "userId") int userId, @RequestBody UpdatingUserForm form) {
        form.setId(userId);
        userService.updateUser(form);
    }

    @PutMapping(value = "/setAdmin/{userId}")
    public void setUserAdmin(@PathVariable(name = "userId") int userId) {
        userService.setUserAdmin(userId);
    }

    @PutMapping(value = "/setMod/{userId}")
    public void setUserMod(@PathVariable(name = "userId") int userId) {
        userService.setUserMod(userId);
    }

    @PutMapping(value = "/setManager/{userId}")
    public void setUserManager(@PathVariable(name = "userId") int userId) {
        userService.setUserManager(userId);
    }

    @PutMapping(value = "/setStaff/{userId}")
    public void setUserStaff(@PathVariable(name = "userId") int userId) {
        userService.setUserStaff(userId);
    }

    @PutMapping(value = "/setMember/{userId}")
    public void setUserMember(@PathVariable(name = "userId") int userId) {
        userService.setUserMember(userId);
    }

    @PutMapping(value = "/active/{userId}")
    public void activeUser(@PathVariable(name = "userId") int userId) {
        userService.activeUser(userId);
    }

}
