package model;
//TODO: define parameters
public class PollinatorVisitCount
{
	private int id, data_form_id, pollinator_id, plant_id, strain_id, visit_count;

	public PollinatorVisitCount(int id, int data_form_id, int pollinator_id, int plant_id, int count)
	{
		this.setId(id);
		this.setData_form_id(data_form_id);
		this.setPollinator_id(pollinator_id);
		this.setPlant_id(plant_id);
		//this.setStrain_id(strain_id);
		this.setVisit_count(count);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getData_form_id() {
		return data_form_id;
	}

	public void setData_form_id(int data_form_id) {
		this.data_form_id = data_form_id;
	}

	public int getPollinator_id() {
		return pollinator_id;
	}

	public void setPollinator_id(int pollinator_id) {
		this.pollinator_id = pollinator_id;
	}

	public int getPlant_id() {
		return plant_id;
	}

	public void setPlant_id(int plant_id) {
		this.plant_id = plant_id;
	}

	public int getVisit_count() {
		return visit_count;
	}

	public void setVisit_count(int visit_count) {
		this.visit_count = visit_count;
	}

	public int getStrain_id() {
		return strain_id;
	}

	public void setStrain_id(int strain_id) {
		this.strain_id = strain_id;
	}
}