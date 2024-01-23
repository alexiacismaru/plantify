package be.kdg.integration.plantifybackend.domain.gson;

import java.util.List;

/**
 * Mapper for the forecasting functionality, used as a simple class to contain the 4 lists of forecasting data per
 * type of measurement
 */
public class PlantForecastingMapper {
    private List<Integer> temperatureAverageForecast;
    private List<Integer> humidityAverageForecast;
    private List<Integer> moistureAverageForecast;
    private List<Integer> lightAverageForecast;

    public PlantForecastingMapper(List<Integer> temperatureAverageForecast, List<Integer> humidityAverageForecast,
                                  List<Integer> moistureAverageForecast, List<Integer> lightAverageForecast) {
        this.temperatureAverageForecast = temperatureAverageForecast;
        this.humidityAverageForecast = humidityAverageForecast;
        this.moistureAverageForecast = moistureAverageForecast;
        this.lightAverageForecast = lightAverageForecast;
    }

    public List<Integer> getTemperatureAverageForecast() {
        return temperatureAverageForecast;
    }

    public List<Integer> getHumidityAverageForecast() {
        return humidityAverageForecast;
    }

    public List<Integer> getMoistureAverageForecast() {
        return moistureAverageForecast;
    }

    public List<Integer> getLightAverageForecast() {
        return lightAverageForecast;
    }
}
