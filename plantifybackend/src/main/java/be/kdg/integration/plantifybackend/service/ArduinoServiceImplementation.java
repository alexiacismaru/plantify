package be.kdg.integration.plantifybackend.service;

import be.kdg.integration.plantifybackend.domain.Arduino;
import be.kdg.integration.plantifybackend.Util.RGBColor;
import be.kdg.integration.plantifybackend.domain.PlantType;
import be.kdg.integration.plantifybackend.repository.ArduinoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * service class for handling arduino information
 */
@Component
public class ArduinoServiceImplementation implements ArduinoService{

    private final ArduinoRepository arduinoRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ArduinoServiceImplementation(ArduinoRepository arduinoRepositoryHibernate) {
        this.arduinoRepository = arduinoRepositoryHibernate;
    }


    @Override
    public List<Arduino> getArduinoList() {
        return arduinoRepository.getArduinoList();
    }

    @Override
    public void setLedSetting(int physicalId, boolean base) {
        arduinoRepository.setLedSetting(physicalId, base);
    }

    /**
     * adds an arduino to teh database
     * @param series type of the arduino
     * @param physicalIdentifier unique ID of the arduino
     * @return function from repository
     */
    @Override
    public Arduino addArduino(String series, int physicalIdentifier) throws SQLException {
        Arduino arduino = new Arduino(series,physicalIdentifier);
        arduinoRepository.saveArduino(arduino);
        return arduino;
    }

    /*@Override
    public void setArduinoList(List<Plant> plantList) {
        this.arduinoRepository.setArduinoList(plantList);
    }*/


    @Override
    public void changeColor(int physicalIdentifier, short red, short blue, short green) {
        arduinoRepository.changeColor(physicalIdentifier, new RGBColor(red, blue, green));
    }

    /**
     * interacts with the arduino to retrieve information being red
     * @param physicalIdentifier unique ID of the arduino to listen to a specific arduino
     * @return all information retrieved from the arduino in a formatted string
     */
    @Override
    public String postMapping(int physicalIdentifier, int command,PlantType plantType,String color) {
        logger.debug(physicalIdentifier + " is the physical identifier");
        Arduino arduino = getArduinoList().stream().filter(ar -> ar.getPhysicalIdentifier() == physicalIdentifier).toList().get(0);
        logger.debug("command is"+command);
        switch (command){
            case 1:
                return String.format("%d%2d%2d%2d%2d%2d%4d",1,plantType.getMinMoisture(),plantType.getMaxMoisture(),plantType.getMinHumidity(),
                plantType.getMinTemp(),plantType.getMaxTemp(),plantType.getMaxBrightness());
            case 2:
                logger.debug("2"+color);
                return "2"+color;
            case 3:
                return "3";
            default:
                return "0";
        }
    }

    @Override
    public void setConfigured(int physicalId,boolean b) {
        arduinoRepository.configureArduino(physicalId,b);
    }

    @Override
    public void removeArduino(int physicalIdentifier){
        arduinoRepository.deleteArduino(physicalIdentifier);
    }
}
