package be.kdg.integration.plantifybackend.domain.gson;

import be.kdg.integration.plantifybackend.domain.Arduino;

import be.kdg.integration.plantifybackend.Util.RGBColor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper for formatting database queries of the arduino table
 */
public class ArduinoMapper implements RowMapper<Arduino> {

    /**
     *
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return Arduino class with values of a single row
     */
    @Override
    public Arduino mapRow(ResultSet rs, int rowNum) throws SQLException {
        Arduino arduino = new Arduino(rs.getString("series"), rs.getInt("physicalIdentifier"));
        arduino.setLedSetting(rs.getBoolean("ledSetting"));
        RGBColor rgbColor = new RGBColor();
        rgbColor.setRed(rs.getShort("redCode"));
        rgbColor.setGreen(rs.getShort("greenCode"));
        rgbColor.setBlue(rs.getShort("blueCode"));
        arduino.setColors(rgbColor);
        return arduino;
    }
}
