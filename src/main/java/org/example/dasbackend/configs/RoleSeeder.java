package org.example.dasbackend.configs;

import org.example.dasbackend.model.userroles.Role;
import org.example.dasbackend.repositories.RoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationRunner {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        roles.add("USER");

        for (String roleEnum : roles) {
            Optional<Role> existingRole = roleRepository.findByName(roleEnum);
            if (existingRole.isEmpty()) {
                Role role = new Role();
                role.setName(roleEnum);
                role.setDescription("Role of: " + roleEnum);
                roleRepository.save(role);
            }
        }
    }
}
