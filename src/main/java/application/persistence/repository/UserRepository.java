package application.persistence.repository;

import application.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long>{

    AppUser findUserByUsername(String username);
}
