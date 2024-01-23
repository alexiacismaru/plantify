package be.kdg.integration.plantifybackend.domain;

/**
 * Different types of plants, useful for future AI in determining water usagepublic
 */
public enum PlantType {
    HERBS(20,60,50,15,21,1500),
    SHRUBS(21,60,60,21,24,1500),
    TREES(21,60,40,20,38,1500),
    CLIMBERS(60,80,40,20,38,1500),
    CREEPERS(21,60,50,21,27,1500);

    private int minMoisture;
    private int maxMoisture;
    private int minHumidity;
    private int minTemp;
    private int maxTemp;
    private int maxBrightness;

    PlantType(int minMoisture, int maxMoisture, int minHumidity, int minTemp, int maxTemp, int maxBrightness) {
        this.minMoisture=minMoisture;
        this.maxMoisture=maxMoisture;
        this.minHumidity=minHumidity;
        this.minTemp=minTemp;
        this.maxTemp=maxTemp;
        this.maxBrightness=maxBrightness;
    }

    public int getMinMoisture() {
        return minMoisture;
    }

    public int getMaxMoisture() {
        return maxMoisture;
    }

    public int getMinHumidity() {
        return minHumidity;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMaxBrightness() {
        return maxBrightness;
    }

    @Override
    public String toString() {
        return String.format("%d %d %d %d %d %d",minMoisture,maxMoisture,minHumidity,minTemp,maxTemp,maxBrightness);
    }


}