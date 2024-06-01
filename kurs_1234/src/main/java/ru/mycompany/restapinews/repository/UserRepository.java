package ru.mycompany.restapinews.repository;

import ru.mycompany.restapinews.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
