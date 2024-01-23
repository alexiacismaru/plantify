package be.kdg.integration.plantifybackend.presentation.viewModel;

import be.kdg.integration.plantifybackend.domain.PlantType;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.ConversionService;

import javax.validation.constraints.*;

/**
 * ViewModel for storing and BeanValidating input from the addplant.html form
 */
public class PlantViewModel {
    /**
     * Name of the plant, can't be empty
     */
    @NotEmpty(message = "Name can't be empty!")
    private String name;

    /**
     * Type of the plant, NotNull is not needed since this is a select input
     */
    @NotNull(message = "type can't be null")
    private PlantType type;

    /**
     * Physical adress of the arduino of the plant, can't be null and can't be negative
     */
    @NotNull(message = "Physical Address can't be empty!")
    @PositiveOrZero(message = "Physical Address can't be negative!")
    private int physicalAddress;

    /**
     * Series of the arduino, can't be null
     */
    @NotEmpty(message = "Arduino Series can't be empty!")
    private String arduinoSeries;

    public int getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(int physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getArduinoSeries() {
        return arduinoSeries;
    }

    public void setArduinoSeries(String arduinoSeries) {
        this.arduinoSeries = arduinoSeries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlantType getType() {
        return type;
    }

    /**
     * uses the converter from StringToPlantTypeConverter.java
     * @param type string from the select input -> never null
     */
    public void setType(String type) {
        ConversionService conversionService= new ApplicationConversionService();
        this.type = conversionService.convert(type, PlantType.class);
    }

    @Override
    public String toString() {
        return "PlantControllerViewModel{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", physicalAddress=" + physicalAddress +
                ", arduinoSeries='" + arduinoSeries + '\'' +
                '}';
    }
}
