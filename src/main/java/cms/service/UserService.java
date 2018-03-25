package cms.service;

import cms.model.User;

public interface UserService {

    User findByUsername(String name);

    void registerUser(User user);
}
