package be.kdg.integration.plantifybackend.presentation.converter;

import be.kdg.integration.plantifybackend.domain.PlantType;
import org.springframework.core.convert.converter.Converter;

import java.util.Locale;


/**
 * Converter for converting String to PlantType object
 */
public class StringToPlantTypeConverter implements Converter<String, PlantType> {
    @Override
    public PlantType convert(String source) {
        return PlantType.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
