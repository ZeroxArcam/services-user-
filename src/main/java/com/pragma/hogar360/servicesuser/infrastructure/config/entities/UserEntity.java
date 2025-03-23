package com.pragma.hogar360.servicesuser.infrastructure.config.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entity class representing a user in the database.
 * This class is mapped to the "users" table and contains information about a user,
 * including their unique ID, name, last name, document ID, phone number, birthdate, email, password, and role.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [20/03/2025]
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    /**
     * The unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the user.
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * The last name of the user.
     */
    @Column(nullable = false, length = 50)
    private String lastName;

    /**
     * The document ID of the user.
     */
    @Column(nullable = false, unique = true)
    private String idNumber;

    /**
     * The phone number of the user.
     */
    @Column(nullable = false, length = 13)
    private String phoneNumber ;

    /**
     * The birthdate of the user.
     */
    @Column(nullable = false)
    private LocalDate birthDate;

    /**
     * The email of the user.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The password of the user (encrypted).
     */
    @Column(nullable = false)
    private String password;

    /**
     * The role of the user.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

}