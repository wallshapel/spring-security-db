package com.app;

import com.app.entities.Permission;
import com.app.entities.Role;
import com.app.entities.RoleEnum;
import com.app.entities.User;
import com.app.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	// Poblamos las tablas permisos y roles al levantar la App
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {

			// Creamos los permisos
			Permission createPermission = Permission.builder()
					.name("CREATE")
					.build();
			Permission readPermission = Permission.builder()
					.name("READ")
					.build();
			Permission updatePermission = Permission.builder()
					.name("UPDATE")
					.build();
			Permission deletePermission = Permission.builder()
					.name("DELETE")
					.build();
			Permission refactorPermission = Permission.builder()
					.name("REFACTOR")
					.build();

			// Creamos los roles
			Role roleAdmin = Role.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();
			Role roleUser = Role.builder()
					.roleEnum(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission))
					.build();
			Role roleInvited = Role.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissions(Set.of(readPermission))
					.build();
			Role roleDeveloper = Role.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			// Creamos algunos usuarios con sus roles
			User userSantiago = User.builder()
					.username("Santiago")
					.password("$2a$10$Q.jvCDXMx1TwexETPmd5V.XqyrkvFqqiof3IsHdNX3HDPw8G8iH8m")
					.isEnabled(true) // La cuenta está activa
					.accountNoExpired(true) // La cuenta no ha expirado
					.accountNoLocked(true) // La cuenta no está bloqueada
					.credentialNoExpired(true) // Las credenciales no están expiradas
					.roles(Set.of(roleAdmin))
					.build();
			User userLegato = User.builder()
					.username("Legato")
					.password("$2a$10$Q.jvCDXMx1TwexETPmd5V.XqyrkvFqqiof3IsHdNX3HDPw8G8iH8m")
					.isEnabled(true) // La cuenta está activa
					.accountNoExpired(true) // La cuenta no ha expirado
					.accountNoLocked(true) // La cuenta no está bloqueada
					.credentialNoExpired(true) // Las credenciales no están expiradas
					.roles(Set.of(roleUser))
					.build();
			User userKaroll = User.builder()
					.username("Karoll")
					.password("$2a$10$Q.jvCDXMx1TwexETPmd5V.XqyrkvFqqiof3IsHdNX3HDPw8G8iH8m")
					.isEnabled(true) // La cuenta está activa
					.accountNoExpired(true) // La cuenta no ha expirado
					.accountNoLocked(true) // La cuenta no está bloqueada
					.credentialNoExpired(true) // Las credenciales no están expiradas
					.roles(Set.of(roleInvited))
					.build();
			User userYesli = User.builder()
					.username("Yesli")
					.password("$2a$10$Q.jvCDXMx1TwexETPmd5V.XqyrkvFqqiof3IsHdNX3HDPw8G8iH8m")
					.isEnabled(true) // La cuenta está activa
					.accountNoExpired(true) // La cuenta no ha expirado
					.accountNoLocked(true) // La cuenta no está bloqueada
					.credentialNoExpired(true) // Las credenciales no están expiradas
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userSantiago, userLegato, userKaroll, userYesli));
		};
	}

}
