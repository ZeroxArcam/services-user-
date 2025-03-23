package com.pragma.hogar360.servicesuser.infrastructure.config.entities;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a role in the database.
 * This class is mapped to the "roles" table and contains information about a role,
 * including its unique ID, name, and description.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [20/03/2025]
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    /**
     * The unique identifier of the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role.
     * It is required, has a maximum length of 50 characters, and must be unique.
     */
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    /**
     * The description of the role.
     * It is required and has a maximum length of 120 characters.
     */
    @Column(length = 120)
    private String description;
}