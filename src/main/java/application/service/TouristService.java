package application.service;

import application.entity.Tourist;
import application.persistence.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {

    @Autowired
    private TouristRepository touristRepository;

    public List<Tourist> getAllTouristsDB(){
        return touristRepository.findAll();
    }

}
