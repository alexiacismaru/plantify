package be.kdg.integration.plantifybackend.domain;

import be.kdg.integration.plantifybackend.Util.RGBColor;

/**
 * Arduino class
 */
public class Arduino {
    /**
     * Shows which type the arduino is (for future implementation of different types of pots)
     */
    private String series;

    /**
     * False means base color setting and true means color is selected by user
     */
    private boolean ledSetting=false;

    /**
     * Instruction for the pump (future implementation and currently out of scope)
     */
    private short pumpInstruction;

    /**
     * Used to identify between different arduinos, unique per arduino
     */
    private int physicalIdentifier;

    /**
     * intializes class for led colors of the led strip
     */
    private RGBColor ledColor= new RGBColor();


    /**
     * Initializer for RowMapper
     */

    private boolean isConfigured = false;
    /**
     * Initializer arduino state
     */
    public Arduino() {
    }

    /**
     * Standard initializer
     * @param series
     * @param physicalIdentifier
     */
    public Arduino(String series, int physicalIdentifier) {
        this.series=series;
        this.physicalIdentifier=physicalIdentifier;
    }


    public Arduino(String series, boolean ledSetting, int physicalIdentifier, RGBColor ledColor, boolean isConfigured) {
        this.series = series;
        this.ledSetting = ledSetting;
        this.physicalIdentifier = physicalIdentifier;
        this.ledColor = ledColor;
        this.isConfigured = isConfigured;
    }

    /**
     * Sets the colors of the led strip
     * @param color RGBColor class with 3 parameters blue, green and red
     */
    public void setColors(RGBColor color){
        // to change values with parameter
        ledColor.setBlue(color.getBlue());
        ledColor.setGreen(color.getGreen());
        ledColor.setRed(color.getRed());

    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getPhysicalIdentifier() {
        return physicalIdentifier;
    }

    public void setPhysicalIdentifier(int physicalIdentifier) {
        this.physicalIdentifier = physicalIdentifier;
    }

    public void setBrightness(short brightness){
        ledColor.setBrightness(brightness);
    }

    public void setLedSetting(boolean setting){
        this.ledSetting=setting;
    }

    public String getSeries() {
        return series;
    }

    public boolean getLedSetting() {
        return ledSetting;
    }

    public short getPumpInstruction() {
        return pumpInstruction;
    }

    public void setPumpInstruction(short pumpInstruction) {
        this.pumpInstruction = pumpInstruction;
    }

    public RGBColor getLedColor() {
        return ledColor;
    }

    public short getBlue(){
        return ledColor.getBlue();
    }
    public short getRed(){
        return ledColor.getRed();
    }
    public short getGreen(){
        return ledColor.getGreen();
    }

    public boolean isConfigured() {
        return isConfigured;
    }

    public void setConfiguration(boolean configured) {
        isConfigured = configured;
    }

    @Override
    public String toString() {
        return "Arduino{" + series+
                ", physicalIdentifier=" + physicalIdentifier +
                ", COLOR=" + ledColor +
                '}';
    }




}
