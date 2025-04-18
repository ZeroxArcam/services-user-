//package com.pragma.hogar360.servicesuser.infrastructure.config.config;
//
//import com.pragma.hogar360.servicesuser.infrastructure.config.entities.RoleEntity;
//import com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import lombok.extern.slf4j.Slf4j;
//import java.util.Optional;
//
//@Component
//@Slf4j
//public class DatabaseConnectionTest implements CommandLineRunner {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Optional<RoleEntity> roleEntityOpt = roleRepository.findByName("seller");
//        if (roleEntityOpt.isPresent()) {
//            log.info("Role found: " + roleEntityOpt.get().getName());
//        } else {
//            log.info("Role not found");
//        }
//    }
//
//}