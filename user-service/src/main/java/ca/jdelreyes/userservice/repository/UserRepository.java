package ca.jdelreyes.userservice.repository;

import ca.jdelreyes.userservice.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long userId);

    User findUserByUserName(String userName);

    void deleteUserById(@NonNull Long userId);

    boolean existsById(@NonNull Long userId);
}
