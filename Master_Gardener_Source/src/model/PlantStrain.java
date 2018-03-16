package model;
//TODO: define parameters
public class PlantStrain
{
	private int strain_id, plant_id;
	private String strain_name, strain_type;

	public PlantStrain(int strain_id, int plant_id, String strain_name, String strain_type)
	{
		this.setStrainID(strain_id);
		this.setPlantID(plant_id);
		this.setStrainName(strain_name);
		this.setStrainType(strain_type);
	}
	
	public String getStrainName()
	{
		return this.strain_name;
	}
	
	public void setStrainName(String strain_name)
	{
		this.strain_name = strain_name;
	}

	public int getStrainID() {
		return strain_id;
	}

	public void setStrainID(int strain_id) {
		this.strain_id = strain_id;
	}

	public int getPlantID() {
		return plant_id;
	}

	public void setPlantID(int plant_id) {
		this.plant_id = plant_id;
	}

	public String getStrainType() {
		return strain_type;
	}

	public void setStrainType(String strain_type) {
		this.strain_type = strain_type;
	}
}
