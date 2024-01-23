package be.kdg.integration.plantifybackend.presentation.viewModel;

import javax.validation.constraints.*;

/**
 * ViewModel for storing and BeanValidating custom colors from the plantList.html color picker
 */
public class PlantSpecificViewModel {
    private String hexadecimal;
    @Min(0)
    @Max(255)
    private int red;
    @Min(0)
    @Max(255)
    private int green ;
    @Min(0)
    @Max(255)
    private int blue;

    /**
     * converts hexadecimal to Integer
     */
    public void hex2Rgb() {
       setRed(Integer.valueOf(this.hexadecimal.substring(1, 3), 16));
       setGreen(Integer.valueOf(this.hexadecimal.substring(3, 5), 16));
       setBlue(Integer.valueOf(this.hexadecimal.substring(5, 7), 16));
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public void setHexadecimal(String hexadecimal) {
        this.hexadecimal = hexadecimal;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return String.format("RGB(%d,%d,%d)", this.red, this.green, this.blue);
    }
}
