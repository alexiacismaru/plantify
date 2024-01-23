package be.kdg.integration.plantifybackend.domain.hibernate;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * class for usage in JPA hibernate, communicates with detailsarchive table
 */
@Entity
@Table(name="detailsarchive")
public class ArchiveDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int plantid;
    int temperatureavg;
    int humidityavg;
    int moistureavg;
    int lightavg;

    Timestamp refreshtime;
    int minimumtemperature;
    int maximumtemperature;
    int minimumhumidity;
    int maximumhumidity;
    int minimummoisture;
    int maximummoisture;
    int minimumlight;
    int maximumlight;
    int totalrowsarchived;

    public ArchiveDao(int id, int plantid, int temperatureavg, int humidityavg, int moistureavg, int lightavg, Timestamp refreshtime, int minimumtemperature, int maximumtemperature, int minimumhumidity, int maximumhumidity, int minimummoisture, int maximummoisture, int minimumlight, int maximumlight, int totalrowsarchived) {
        this.id = id;
        this.plantid = plantid;
        this.temperatureavg = temperatureavg;
        this.humidityavg = humidityavg;
        this.moistureavg = moistureavg;
        this.lightavg = lightavg;
        this.refreshtime = refreshtime;
        this.minimumtemperature = minimumtemperature;
        this.maximumtemperature = maximumtemperature;
        this.minimumhumidity = minimumhumidity;
        this.maximumhumidity = maximumhumidity;
        this.minimummoisture = minimummoisture;
        this.maximummoisture = maximummoisture;
        this.minimumlight = minimumlight;
        this.maximumlight = maximumlight;
        this.totalrowsarchived = totalrowsarchived;
    }

    public ArchiveDao() {
    }

    @Override
    public String toString() {
        return "ArchiveDao{" +
                "id=" + id +
                ", plantid=" + plantid +
                ", temperatureavg=" + temperatureavg +
                ", humidityavg=" + humidityavg +
                ", moistureavg=" + moistureavg +
                ", lightavg=" + lightavg +
                ", refreshtime=" + refreshtime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlantid() {
        return plantid;
    }

    public void setPlantid(int plantid) {
        this.plantid = plantid;
    }

    public int getTemperatureavg() {
        return temperatureavg;
    }

    public void setTemperatureavg(int temperatureavg) {
        this.temperatureavg = temperatureavg;
    }

    public int getHumidityavg() {
        return humidityavg;
    }

    public void setHumidityavg(int humidityavg) {
        this.humidityavg = humidityavg;
    }

    public int getMoistureavg() {
        return moistureavg;
    }

    public void setMoistureavg(int moistureavg) {
        this.moistureavg = moistureavg;
    }

    public int getLightavg() {
        return lightavg;
    }

    public void setLightavg(int lightavg) {
        this.lightavg = lightavg;
    }

    public Timestamp getRefreshtime() {
        return refreshtime;
    }

    public void setRefreshtime(Timestamp refreshtime) {
        this.refreshtime = refreshtime;
    }

    public int getMinimumtemperature() {
        return minimumtemperature;
    }

    public void setMinimumtemperature(int minimumtemperature) {
        this.minimumtemperature = minimumtemperature;
    }

    public int getMaximumtemperature() {
        return maximumtemperature;
    }

    public void setMaximumtemperature(int maximumtemperature) {
        this.maximumtemperature = maximumtemperature;
    }

    public int getMinimumhumidity() {
        return minimumhumidity;
    }

    public void setMinimumhumidity(int minimumhumidity) {
        this.minimumhumidity = minimumhumidity;
    }

    public int getMaximumhumidity() {
        return maximumhumidity;
    }

    public void setMaximumhumidity(int maximumhumidity) {
        this.maximumhumidity = maximumhumidity;
    }

    public int getMinimummoisture() {
        return minimummoisture;
    }

    public void setMinimummoisture(int minimummoisture) {
        this.minimummoisture = minimummoisture;
    }

    public int getMaximummoisture() {
        return maximummoisture;
    }

    public void setMaximummoisture(int maximummoisture) {
        this.maximummoisture = maximummoisture;
    }

    public int getMinimumlight() {
        return minimumlight;
    }

    public void setMinimumlight(int minimumlight) {
        this.minimumlight = minimumlight;
    }

    public int getMaximumlight() {
        return maximumlight;
    }

    public void setMaximumlight(int maximumlight) {
        this.maximumlight = maximumlight;
    }

    public int getTotalrowsarchived() {
        return totalrowsarchived;
    }

    public void setTotalrowsarchived(int totalrowsarchived) {
        this.totalrowsarchived = totalrowsarchived;
    }
}
