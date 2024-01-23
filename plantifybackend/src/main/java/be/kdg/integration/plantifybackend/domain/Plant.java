package be.kdg.integration.plantifybackend.domain;

import java.io.Serializable;

/**
 * Plant class with .Details for details of the current state of the plant
 */
public class Plant implements Serializable {

    /**
     * Details of the current state of the plant, replacement for plantData
     */
    public class Details{
        private double temperature;
        private double brightness;
        private double humidity;
        private double moisture;


        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public void setBrightness(double brightness) {
            this.brightness = brightness;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public void setMoisture(double moisture) {
            this.moisture = moisture;
        }

        public double getTemperature() {
            return temperature;
        }

        public double getBrightness() {
            return brightness;
        }

        public double getHumidity() {
            return humidity;
        }

        public double getMoisture() {
            return moisture;
        }

        @Override
        public String toString() {
            return "Details{" +
                    "temperature=" + temperature +
                    ", brightness=" + brightness +
                    ", humidity=" + humidity +
                    ", moisture=" + moisture +
                    '}';
        }
    }

    /**
     * Name of the plant
     */
    private String name;

    /**
     * Type of the plant, PlantType.class
     */
    private PlantType typeOfPlant;

    /**
     * Arduino which is monitoring this plant
     */
    private Arduino arduino;

    /**
     * Unique ID of the plant
     */
    private int id;

    /**
     * Email of the owner of the plant
     */
    private String emailUser;

    /**
     * Initializer of Details of the plant
     */
    private final Details details=new Details();



    /**
     * Initializer for RowMappers
     */
    public Plant() {
    }

    /**
     * Standard initializer
     * @param name
     * @param typeOfPlant
     * @param arduino
     */
    public Plant(String name, PlantType typeOfPlant, Arduino arduino, String emailUser) {
        this.name = name;
        this.typeOfPlant = typeOfPlant;
        this.arduino = arduino;
        this.emailUser= emailUser;
    }

    public Plant(String name, PlantType typeOfPlant, Arduino arduino, int id, String emailUser) {
        this.name = name;
        this.typeOfPlant = typeOfPlant;
        this.arduino = arduino;
        this.id = id;
        this.emailUser = emailUser;
    }

    public void setDetails(double moisture,double temp, double humid,double brightness){
        this.details.setMoisture(moisture);
        this.details.setTemperature(temp);
        this.details.setHumidity(humid);
        this.details.setBrightness(brightness);
    }

    public void setDetails(Details details){
        this.details.setHumidity(details.getHumidity());
        this.details.setTemperature(details.getTemperature());
        this.details.setMoisture(details.getMoisture());
        this.details.setBrightness(details.getBrightness());
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public Arduino getArduino() {
        return arduino;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlantType getTypeOfPlant() {
        return typeOfPlant;
    }

    public void setTypeOfPlant(PlantType typeOfPlant) {
        this.typeOfPlant = typeOfPlant;
    }


    public Details getDetails() {
        return details;
    }


    public void setArduino(Arduino arduino) {
        this.arduino = arduino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", typeOfPlant=" + typeOfPlant +
                ", arduino=" + arduino +
                ", id=" + id +
                ", emailUser='" + emailUser + '\'' +
                ", details=" + details +
                '}';
    }
}


