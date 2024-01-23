package be.kdg.integration.plantifybackend.domain.hibernate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * class for usage in JPA hibernate, communicates with details table
 */
@Entity
@Table(name = "details")
public class DetailsDao {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "plantid")
    private int plantId;
    @Column(name = "temperature", nullable = false, updatable = false)
    private double temperature;
    @Column(name = "humidity", nullable = false, updatable = false)
    private double humidity;
    @Column(name = "moisture", nullable = false, updatable = false)
    private double moisture;
    @Column(name = "light", nullable = false, updatable = false)
    private double light;
    @Column(name = "refreshtime", updatable = false)
    private Timestamp refreshTime;

    public DetailsDao(int plantId, double temperature, double humidity, double moisture, double light) {
        this.plantId = plantId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.moisture = moisture;
        this.light = light;
    }

    public DetailsDao() {

    }

    public Timestamp getRefreshTime() {
        return refreshTime;
    }

    public double getLight() {
        return light;
    }

    public double getMoisture() {
        return moisture;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getPlantId() {
        return plantId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DetailsDao{" +
                "id=" + id +
                ", plantId=" + plantId +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", moisture=" + moisture +
                ", light=" + light +
                ", refreshTime=" + refreshTime +
                '}';
    }
}
