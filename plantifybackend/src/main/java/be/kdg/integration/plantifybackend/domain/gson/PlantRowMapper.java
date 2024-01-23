package be.kdg.integration.plantifybackend.domain.gson;

import be.kdg.integration.plantifybackend.domain.Arduino;
import be.kdg.integration.plantifybackend.domain.Plant;
import be.kdg.integration.plantifybackend.domain.PlantType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Mapper for formatting database queries of the plant table
 */
public class PlantRowMapper implements RowMapper<Plant> {

    /**
     *
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return a Plant object with plantID, plantname, planttype and arduino
     */
    @Override
    public Plant mapRow(ResultSet rs, int rowNum) throws SQLException {

        Arduino arduino = new Arduino();
        arduino.setPhysicalIdentifier(rs.getInt("arduinophysicalidentifier"));
        arduino.setSeries(rs.getString("series"));
        Plant plant = new Plant();
        plant.setId(rs.getInt("plantid"));
        plant.setEmailUser(rs.getString("useremail"));
        plant.setName(rs.getString("plantname"));
        plant.setTypeOfPlant(PlantType.valueOf(rs.getString("planttype").toUpperCase(Locale.ROOT)));
        plant.setArduino(arduino);
        return plant;

    }

}
