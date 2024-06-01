package ru.mycompany.restapinews.config;

import ru.mycompany.restapinews.model.User;
import ru.mycompany.restapinews.repository.UserRepository;
import ru.mycompany.restapinews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private UserService userRoleService;

    @Autowired
    private UserRepository userRoleRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (userRoleRepository.findByUsername("user") == null){
                User user = new User();
                user.setUsername("user");
                user.setPassword("1111");
                user.setRole("ROLE_USER");
                userRoleService.saveUser(user);
            }

            if (userRoleRepository.findByUsername("admin") == null){
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin");
                admin.setRole("ROLE_ADMIN");
                userRoleService.saveUser(admin);
            }
        };
    }
}
