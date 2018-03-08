package application.service;

import application.entity.Tour;
import application.persistence.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public void saveTour(Tour tour){
        tourRepository.save(tour);
    }
}
