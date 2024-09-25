package app.server.inventory.auth.repository;

import app.server.inventory.auth.entities.AuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends CrudRepository<AuthUser, Integer> {
   Optional<AuthUser> findByEmail(String email);
}
