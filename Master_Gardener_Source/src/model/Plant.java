package model;

public class Plant
{
    private int plantID = -1;
    private String plant_name = "";

    public Plant(int plantID, String plant_name)
    {
        this.plantID = plantID;
        this.plant_name = plant_name;
    }

    public void setPlantID(int plantID)
    {
        this.plantID = plantID;
    }

    public int getPlantID()
    {
        return this.plantID;
    }

    public String getPlantName() {
        return plant_name;
    }

    public void setPlantName(String plant_name) {
        this.plant_name = plant_name;
    }
}
