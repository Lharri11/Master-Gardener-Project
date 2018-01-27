package model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class PollinatorDataForm
{private int data_form_id;
	private int garden_id;
	private int county_id;
	private int generator_id;
	private int researcher_id = -1;
	private int week_number;
	private int temperature;
	private ArrayList<Plot> plot;

	private LocalDate date_created;
	private LocalDate date_generated;
	private LocalDate date_approved;
	private LocalTime monitor_start;
	private LocalTime monitor_stop;
	private String comments;
	private String cloud_status;
	private String wind_status;
	private ArrayList<PollinatorVisitCount> pvc;
	private ArrayList<User> gardeners;
	private ArrayList<Pollinator> pollinators;
	private Plant plant;
	private ArrayList<PlantStrain> plantStrains;
	private int confirmed;

	public PollinatorDataForm(int data_form_id, int garden_id, int county_id, int generator_id, int week_number, int temperature, LocalDate date_created, LocalDate date_generated,
							  LocalTime monitor_start, LocalTime monitor_stop, String comments, String cloud_status, String wind_status, ArrayList<Plot> plot, Plant plant, ArrayList<PlantStrain> strain,
							  ArrayList<Pollinator> poll, ArrayList<PollinatorVisitCount> pvc)
	{
		this.data_form_id = data_form_id;
		this.garden_id = garden_id;
		this.county_id = county_id;
		this.generator_id = generator_id;
		this.week_number = week_number;
		this.temperature = temperature;
		this.date_created = date_created;
		this.date_generated = date_generated;
		this.monitor_start = monitor_start;
		this.monitor_stop = monitor_stop;
		this.comments = comments;
		this.cloud_status = cloud_status;
		this.wind_status = wind_status;
		this.plot = plot;
		this.plant = plant;
		this.plantStrains = strain;
		this.pollinators = poll;
		this.pvc = pvc;
		this.confirmed = 0;
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

	public int getResearcher_id(){
		return this.researcher_id;
	}

	public void setResearcher_id(int researcher_id)
	{
		this.researcher_id = researcher_id;
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

	public String getCloud_status() {
		return cloud_status;
	}

	public void setCloud_status(String cloud_status) {
		this.cloud_status = cloud_status;
	}

	public String getWind_status() {
		return wind_status;
	}

	public void setWind_status(String wind_status) {
		this.wind_status = wind_status;
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

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public ArrayList<PlantStrain> getPlantStrains() {
		return plantStrains;
	}

	public void setPlantStrains(ArrayList<PlantStrain> plantStrains) {
		this.plantStrains = plantStrains;
	}

	public ArrayList<PollinatorVisitCount> getPollinatorVisitCounts() {
		return pvc;
	}

	public void setPollinatorVisitCounts(ArrayList<PollinatorVisitCount> pvc) {
		this.pvc = pvc;
	}

	public void setPlot(ArrayList<Plot> plot)
	{
		this.plot = plot;
	}

	public ArrayList<Plot> getPlot()
	{
		return this.plot;
	}
	public int getConfirmedStatus()
	{
		return this.confirmed;
	}

	public void setConfirmedStatus(int confirmed)
	{
		this.confirmed = confirmed;
	}

}