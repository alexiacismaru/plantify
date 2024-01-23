package be.kdg.integration.plantifybackend.domain.gson;

import be.kdg.integration.plantifybackend.domain.Plant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper for formatting database queries of the plantCurrentData table,
 * only used for updateDBArchive()
 */
public class PlantDetailsRowMapper implements RowMapper<Plant> {

    /**
     *
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return a Plant object with only a plantID and plantDetails
     */
    @Override
    public Plant mapRow(ResultSet rs, int rowNum) throws SQLException{
        Plant plant = new Plant();
        Plant.Details plantData = plant.getDetails();
        plant.setId(rs.getInt("plantId"));
        plantData.setTemperature(rs.getInt("temperature"));
        plantData.setHumidity(rs.getInt("humidity"));
        plantData.setMoisture(rs.getInt("moisture"));
        plantData.setBrightness(rs.getInt("light"));
        return plant;
    }
}
