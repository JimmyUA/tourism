package application.persistence.repository;

import application.entity.database.TouristBD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristRepository extends JpaRepository<TouristBD, Long>{
}
