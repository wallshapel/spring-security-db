package com.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled; // Requerido para spring security

    @Column(name = "account_no_expired")
    private boolean accountNoExpired; // Requerido para spring security

    @Column(name = "account_no_locked")
    private boolean accountNoLocked; // Requerido para spring security

    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;


    // RELACIONES

    // Un usuario puede tener muchos roles y un role puede estar asignado a muchos uisuarios. Entonces la relación es muchos a muchos
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Eager: Cuando se recupera una instancia de la entidad, todas las instancias relacionadas se cargan automáticamente y están disponibles. All: cualquier operación de persistencia realizada en la entidad propietaria se propagará a las entidades relacionadas.
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")) // Nombre de la tabla intermedia que se creará y sus respectivas columnas
    private Set<Role> roles = new HashSet<>(); // Usamos Set en vez de List, para que no haya elementos repetidos


}
