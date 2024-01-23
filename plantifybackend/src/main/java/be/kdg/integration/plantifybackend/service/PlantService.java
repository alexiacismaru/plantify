package be.kdg.integration.plantifybackend.service;

import be.kdg.integration.plantifybackend.domain.Arduino;
import be.kdg.integration.plantifybackend.domain.Client;
import be.kdg.integration.plantifybackend.domain.Plant;
import be.kdg.integration.plantifybackend.domain.PlantType;
import be.kdg.integration.plantifybackend.domain.gson.PlantForecastingMapper;
import be.kdg.integration.plantifybackend.domain.hibernate.ArchiveDao;

import java.sql.SQLException;
import java.util.List;

public interface PlantService {
    Plant addPlant(String name, PlantType plantType, Arduino arduino, Client client);

    void saveReadingsToDB(Plant.Details details, int plantId);
    void updateDBArchive();

    Plant getPlantByPhysId(int physId);

    List<Plant> readPlants();

    List<ArchiveDao> getArchiveByPlantId(int id);


    int getPlantPhysicalIdentifier(int plantId);

    void removePlant(int id);

    PlantForecastingMapper getForecastingData(int plantId) throws SQLException;

    List<Integer> getSoilMoistureForecasting(int plantId) throws SQLException;

    List<Integer> getBrightnessForecasting(int plantId) throws SQLException;

    List<Integer> getAirhumidityForecasting(int plantId) throws SQLException;
    List<Integer> getTemperatureForecasting(int plantId) throws SQLException;
}
