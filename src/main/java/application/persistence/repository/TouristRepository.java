package application.persistence.repository;

import application.entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristRepository extends JpaRepository<Tourist, Long>{

    Tourist getByMobile(String mobile);
}
