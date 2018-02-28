package application.entity.database;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tourists")
public class TouristBD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "bonus_amount")
    private Integer bonusAmount;

    @Column(name = "used_bonuses_amount")
    private Integer usedBonuses;

    @Column(name = "tourist_info_id")
    private Long touristInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Integer bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Integer getUsedBonuses() {
        return usedBonuses;
    }

    public void setUsedBonuses(Integer usedBonuses) {
        this.usedBonuses = usedBonuses;
    }

    public Long getTouristInfoId() {
        return touristInfoId;
    }

    public void setTouristInfoId(Long touristInfoId) {
        this.touristInfoId = touristInfoId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "TouristBD{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", parentId=" + parentId +
                ", bonusAmount=" + bonusAmount +
                ", usedBonuses=" + usedBonuses +
                ", touristInfoId=" + touristInfoId +
                '}';
    }
}
