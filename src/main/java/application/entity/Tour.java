package application.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tours")
public class Tour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tourist_id")
    private Tourist luckyGuy;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "tour_info_id")
    private Integer tourDetailsId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tourist getLuckyGuy() {
        return luckyGuy;
    }

    public void setLuckyGuy(Tourist luckyGuy) {
        this.luckyGuy = luckyGuy;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getTourDetailsId() {
        return tourDetailsId;
    }

    public void setTourDetailsId(Integer tourDetailsId) {
        this.tourDetailsId = tourDetailsId;
    }


    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", luckyGuy=" + luckyGuy +
                ", cost=" + cost +
                ", tourDetailsId=" + tourDetailsId +
                '}';
    }
}
