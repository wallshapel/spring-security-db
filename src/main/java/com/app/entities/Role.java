package com.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;


    // RELACIONES

    // Un role puede tener varios permisos y un permiso puede estar asignado a diferentes roles. Entonces la relación es muchos a muchos
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Eager: Cuando se recupera una instancia de la entidad, todas las instancias relacionadas se cargan automáticamente y están disponibles. All: cualquier operación de persistencia realizada en la entidad propietaria se propagará a las entidades relacionadas.
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id")) // Nombre de la tabla intermedia que se creará y sus respectivas columnas
    private Set<Permission> permissions = new HashSet<>(); // Usamos Set en vez de List, para que no haya elementos repetidos


}
