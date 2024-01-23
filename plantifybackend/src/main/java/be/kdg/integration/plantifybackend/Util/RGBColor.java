package be.kdg.integration.plantifybackend.Util;

/**
 * RGBColor class for colors and brightness of led strip
 */
public class RGBColor {
    private short red=0;
    private short blue=0;
    private short green=0;
    private short brightness;

    public RGBColor() {
    }

    public RGBColor(short red, short blue, short green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public short getRed() {
        return red;
    }

    public short getBlue() {
        return blue;
    }

    public short getGreen() {
        return green;
    }

    public short getBrightness() {
        return brightness;
    }

    public void setRed(short red) {
        this.red = red;
    }

    public void setBlue(short blue) {
        this.blue = blue;
    }

    public void setGreen(short green) {
        this.green = green;
    }

    public void setBrightness(short brightness) {
        this.brightness = brightness;
    }

    @Override
    public String toString() {
        return "RGBColor{" +
                "red=" + red +
                ", blue=" + blue +
                ", green=" + green +
                '}';
    }
}
