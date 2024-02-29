package ca.jdelreyes.userservice.service;

import ca.jdelreyes.userservice.model.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public User getUser();

    public User createUser();

    public User updateUser();

    public void deleteUser();
}
