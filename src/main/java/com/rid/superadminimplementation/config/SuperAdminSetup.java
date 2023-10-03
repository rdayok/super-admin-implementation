package com.rid.superadminimplementation.config;

import com.rid.superadminimplementation.data.models.User;
import com.rid.superadminimplementation.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import static com.rid.superadminimplementation.data.models.Role.SUPER_ADMIN;

@Service
@RequiredArgsConstructor
public class SuperAdminSetup implements CommandLineRunner {

    private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        if (superAdminDoesNotExist()) {
            createInitialAdmin();
        }
    }

    private void createInitialAdmin()  {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Enter super admin username: ");
            String superAdminUserName = reader.readLine().trim();

            System.out.println("Enter super admin password: ");
            String superAdminPassword = reader.readLine().trim();

            User superAdmin = new User();
            superAdmin.setUsername(superAdminUserName);
            superAdmin.setPassword(superAdminPassword);
            superAdmin.setRole(SUPER_ADMIN);
            userRepository.save(superAdmin);

            System.out.println("Super admin account created succesfully. ");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

    }

    private boolean superAdminDoesNotExist() {
        return  userRepository.findByRole(SUPER_ADMIN) == null;
    }
}
