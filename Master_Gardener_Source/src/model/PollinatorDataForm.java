package model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class PollinatorDataForm
{
	private int data_form_id, garden_id, county_id, generator_id, week_number, temperature;

	private LocalDate date_created, date_generated;
	private LocalTime monitor_start, monitor_stop;
	private String plant_vigor_notes, comments;
	private char cloud_status, wind_status;
	private ArrayList<User> gardeners;
	private ArrayList<Pollinator> pollinators;
	private ArrayList<Plant> plants;
	private ArrayList<PlantStrain> plantStrains;

	public PollinatorDataForm(int data_form_id, int garden_id, int county_id, int generator_id, int week_number, int temperature, LocalDate date_created, LocalDate date_generated,
							  LocalTime monitor_start, LocalTime monitor_stop, String plant_vigor_notes, String comments, char cloud_status, char wind_status)
	{
		this.setData_form_id(data_form_id);
		this.setGarden_id(garden_id);
		this.setCounty_id(county_id);
		this.setGenerator_id(generator_id);
		this.setWeek_number(week_number);
		this.setTemperature(temperature);
		this.setDate_created(date_created);
		this.setDate_generated(date_generated);
		this.setMonitor_start(monitor_start);
		this.setMonitor_stop(monitor_stop);
		this.setCloud_status(cloud_status);
		this.setWind_status(wind_status);
		this.setPlant_vigor_notes(plant_vigor_notes);
		this.setComments(comments);
	}

	public int getData_form_id() {
		return data_form_id;
	}

	public void setData_form_id(int data_form_id) {
		this.data_form_id = data_form_id;
	}

	public int getGarden_id() {
		return garden_id;
	}

	public void setGarden_id(int garden_id) {
		this.garden_id = garden_id;
	}

	public int getCounty_id() {
		return county_id;
	}

	public void setCounty_id(int county_id) {
		this.county_id = county_id;
	}

	public int getGenerator_id() {
		return generator_id;
	}

	public void setGenerator_id(int generator_id) {
		this.generator_id = generator_id;
	}

	public int getWeek_number() {
		return week_number;
	}

	public void setWeek_number(int week_number) {
		this.week_number = week_number;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public LocalDate getDate_created() {
		return date_created;
	}

	public void setDate_created(LocalDate date_created) {
		this.date_created = date_created;
	}

	public LocalDate getDate_generated() {
		return date_generated;
	}

	public void setDate_generated(LocalDate date_generated) {
		this.date_generated = date_generated;
	}

	public LocalTime getMonitor_start() {
		return monitor_start;
	}

	public void setMonitor_start(LocalTime monitor_start) {
		this.monitor_start = monitor_start;
	}

	public LocalTime getMonitor_stop() {
		return monitor_stop;
	}

	public void setMonitor_stop(LocalTime monitor_stop) {
		this.monitor_stop = monitor_stop;
	}

	public char getCloud_status() {
		return cloud_status;
	}

	public void setCloud_status(char cloud_status) {
		this.cloud_status = cloud_status;
	}

	public char getWind_status() {
		return wind_status;
	}

	public void setWind_status(char wind_status) {
		this.wind_status = wind_status;
	}

	public String getPlant_vigor_notes() {
		return plant_vigor_notes;
	}

	public void setPlant_vigor_notes(String plant_vigor_notes) {
		this.plant_vigor_notes = plant_vigor_notes;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	public ArrayList<User> getGardeners() {
		return gardeners;
	}

	public void setGardeners(ArrayList<User> gardeners) {
		this.gardeners = gardeners;
	}

	public ArrayList<Pollinator> getPollinators() {
		return pollinators;
	}

	public void setPollinators(ArrayList<Pollinator> pollinators) {
		this.pollinators = pollinators;
	}

	public ArrayList<Plant> getPlants() {
		return plants;
	}

	public void setPlants(ArrayList<Plant> plants) {
		this.plants = plants;
	}

	public ArrayList<PlantStrain> getPlantStrains() {
		return plantStrains;
	}

	public void setPlantStrains(ArrayList<PlantStrain> plantStrains) {
		this.plantStrains = plantStrains;
	}
}