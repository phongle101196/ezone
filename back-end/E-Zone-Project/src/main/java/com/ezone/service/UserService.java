package com.ezone.service;

import com.ezone.config.CustomUserDetails;
import com.ezone.entity.Gender;
import com.ezone.entity.Role;
import com.ezone.entity.User;
import com.ezone.form.create.CreatingUserForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingUserForm;
import com.ezone.repository.IUserRepository;
import com.ezone.specification.UserSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<User> getAllUser(Pageable pageable, FilterForm form) {
        Specification<User> where = UserSpecification.buildWhere(form);
        return userRepository.findAll(where, pageable);
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public void createNewUser(CreatingUserForm form) {
        TypeMap<CreatingUserForm, User> typeMap = modelMapper.getTypeMap(CreatingUserForm.class, User.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingUserForm, User>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        User user = modelMapper.map(form, User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(UpdatingUserForm form) {
        User user = userRepository.findById(form.getId()).get();
        user.setAvatar(form.getAvatar());
        user.setEmail(form.getEmail());
        user.setGender(Gender.valueOf(form.getGender()));
        user.setPhoneNumber(form.getPhoneNumber());
        user.setFullName(form.getFullName());
        user.setAddress(form.getAddress());

        userRepository.save(user);
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void setUserAdmin(int userId) {
        User user = userRepository.findById(userId).get();
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    @Override
    public void setUserMod(int userId) {
        User user = userRepository.findById(userId).get();
        user.setRole(Role.MOD);
        userRepository.save(user);
    }

    @Override
    public void setUserManager(int userId) {
        User user = userRepository.findById(userId).get();
        user.setRole(Role.MANAGER);
        userRepository.save(user);
    }

    @Override
    public void setUserStaff(int userId) {
        User user = userRepository.findById(userId).get();
        user.setRole(Role.STAFF);
        userRepository.save(user);
    }

    @Override
    public void setUserMember(int userId) {
        User user = userRepository.findById(userId).get();
        user.setRole(Role.MEMBER);
        userRepository.save(user);
    }

    @Override
    public void activeUser(int userId) {
        User user = userRepository.findById(userId).get();
        user.setActivated(true);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user);
    }
}
