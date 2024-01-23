package be.kdg.integration.plantifybackend.domain.hibernate;

import be.kdg.integration.plantifybackend.domain.Arduino;
import be.kdg.integration.plantifybackend.domain.PlantType;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * class for usage in JPA hibernate, communicates with plant table
 */
@Entity
@Table(name = "plant")
public class PlantDao {



    @Id
    @Column(name="plantid")
    private int plantid;

    @Column(name = "useremail")
    private String userEmail;

    @Column(name = "plantname", updatable = false)
    private String plantName;

    @Column(name = "planttype", updatable = false)
    @Enumerated(EnumType.STRING)
    private PlantType plantType;

    @Column(name = "dateadded", updatable = false)
    private Timestamp dateAdded;


    @Column(name = "arduinophysicalidentifier")
    private Integer physicalIdentifier;

    public PlantDao(int plantid, String userEmail, String plantName, PlantType plantType, int physicalIdentifier) {
        this.plantid = plantid;
        this.userEmail = userEmail;
        this.plantName = plantName;
        this.plantType = plantType;
        this.dateAdded = Timestamp.from(Instant.now());
        this.physicalIdentifier = physicalIdentifier;
    }

    public PlantDao() {

    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public String getPlantName() {
        return plantName;
    }

    public int getPlantId() {
        return plantid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getPhysicalIdentifier() {
        return physicalIdentifier;
    }

    @Override
    public String toString() {
        return "PlantDao{" +
                "plantid=" + plantid +
                ", userEmail='" + userEmail + '\'' +
                ", plantName='" + plantName + '\'' +
                ", plantType=" + plantType +
                ", dateAdded=" + dateAdded +
                ", physicalIdentifier=" + physicalIdentifier +
                '}';
    }
}
