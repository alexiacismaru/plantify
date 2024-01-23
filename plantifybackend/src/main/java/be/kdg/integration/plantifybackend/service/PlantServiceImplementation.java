package be.kdg.integration.plantifybackend.service;

import be.kdg.integration.plantifybackend.domain.Arduino;
import be.kdg.integration.plantifybackend.domain.Client;
import be.kdg.integration.plantifybackend.domain.Plant;
import be.kdg.integration.plantifybackend.domain.PlantType;
import be.kdg.integration.plantifybackend.domain.gson.PlantForecastingMapper;
import be.kdg.integration.plantifybackend.domain.hibernate.ArchiveDao;
import be.kdg.integration.plantifybackend.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * service class for handling plant data
 */
@Component
public class PlantServiceImplementation implements PlantService{

    private PlantRepository plantRepository;

    @Autowired
    public PlantServiceImplementation(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    /**
     * adds a plant to the database
     * @param name name of the plant
     * @param plantType enum type of the plant
     * @param arduino arduino connected to the plant
     * @param client the owner of the plant
     * @return function from the repositor y
     */
    @Override
    public Plant addPlant(String name, PlantType plantType, Arduino arduino, Client client) {
        Plant plant = plantRepository.setPlantId(new Plant(name,plantType,arduino, client.getEmail()));
        return plantRepository.savePlant(plant, client);
    }

    /**
     * gets all the plant
     * @return function from the repository
     */
    public List<Plant> readPlants(){
        return plantRepository.getPlants();
    }


    @Override
    public Plant getPlantByPhysId(int physId) {
        return plantRepository.getPlants().stream().filter(plant -> plant.getArduino().getPhysicalIdentifier()==physId).toList().get(0);

    }

    @Override
    public List<ArchiveDao> getArchiveByPlantId(int id) {
        return plantRepository.getArchiveDaos().stream().filter(archiveDao -> archiveDao.getPlantid()==id).toList();
    }

    /**
     * calls function from the repository to update plant data based on ID of the plant
     * @param details readings from the arduino
     * @param plantId id of the plant that is being read
     */
    @Override
    public void saveReadingsToDB(Plant.Details details, int plantId){
        plantRepository.saveCurrentReadingsToDB(details, plantId);
    }

    @Override
    public int getPlantPhysicalIdentifier(int plantId) {
        return plantRepository.getPhysicalIdentifier(plantId);
    }

    /**
     * calls function from the repository to update the archive
     */
    @Override
    public void updateDBArchive(){
        plantRepository.updateDBArchive();
    }

//    Refactor

    /**
     * calls function from the repository to delete a plant
     * @param id
     */
    @Override
    public void removePlant(int id) {
        plantRepository.deletePlant(id);
    }

    @Override
    public PlantForecastingMapper getForecastingData(int plantId) throws SQLException {
        return plantRepository.getForecastingData(plantId);
    }

    @Override
    public List<Integer> getSoilMoistureForecasting(int plantId) throws SQLException {
        return plantRepository.getForecastingData(plantId).getMoistureAverageForecast();
    }

    @Override
    public List<Integer> getBrightnessForecasting(int plantId) throws SQLException {
        return plantRepository.getForecastingData(plantId).getLightAverageForecast();
    }
    @Override
    public List<Integer> getAirhumidityForecasting(int plantId) throws SQLException {
        return plantRepository.getForecastingData(plantId).getHumidityAverageForecast();
    }
    @Override
    public List<Integer> getTemperatureForecasting(int plantId) throws SQLException {
        return plantRepository.getForecastingData(plantId).getTemperatureAverageForecast();
    }
}
