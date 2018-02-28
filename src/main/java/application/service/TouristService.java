package application.service;

import application.entity.database.TouristBD;
import application.persistence.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.ManagedBean;
import java.util.List;

@Service
public class TouristService {

    @Autowired
    private TouristRepository touristRepository;

    public List<TouristBD> getAllTouristsDB(){
        return touristRepository.findAll();
    }
}
