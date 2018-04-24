package model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class PollinatorDataForm
{private int data_form_id;
	private int garden_id;
	private int county_id;
	private ArrayList<Integer> generator_ids;
	private int researcher_id = -1;
	private int who_submitted_id;
	private int who_confirmed_id;
	private int week_number;
	private int temperature;
	private ArrayList<Plot> plots;
	private ArrayList<PollinatorVisitCount> visitCounts;
	private ArrayList<User> generators;
	private ArrayList<Pollinator> pollinators;
	private ArrayList<Plant> plants;
	private ArrayList<PlantStrain> strains;

	private LocalDate date_collected;
	private LocalDate date_generated;
	private LocalDate date_confirmed;
	private LocalTime monitor_start;
	private LocalTime monitor_stop;
	private String comments;
    private String butterfly_moth_comments = null;
	private String cloud_status;
	private String wind_status;
	private int confirmed;

	public PollinatorDataForm(int garden_id, int county_id, ArrayList<User> generators, int week_number, LocalDate date_collected,
                              LocalDate date_generated, LocalDate date_confirmed, String wind_status, String cloud_status, String comments,
                              String butterfly_moth_comments, int temperature, LocalTime monitor_start, LocalTime monitor_stop, int confirmed,
							  ArrayList<Plant> plants, ArrayList<PlantStrain> strains, ArrayList<Plot> plots, ArrayList<Pollinator> pollinators,
                              ArrayList<PollinatorVisitCount> visitCounts)
	{
		this.garden_id = garden_id;
		this.county_id = county_id;
		this.generators = generators;
		this.week_number = week_number;
        this.date_collected = date_collected;
        this.date_generated = date_generated;
        this.date_confirmed = date_confirmed;
        this.wind_status = wind_status;
        this.cloud_status = cloud_status;
        this.comments = comments;
        this.butterfly_moth_comments = butterfly_moth_comments;
		this.temperature = temperature;
		this.monitor_start = monitor_start;
		this.monitor_stop = monitor_stop;
        this.confirmed = 0;
		this.plots = plots;
		this.plants = plants;
		this.strains = strains;
		this.pollinators = pollinators;
		this.visitCounts = visitCounts;
	}

    public String getButterflyMothComments()
	{
		return this.butterfly_moth_comments;
	}

	public void setButterflyMothComments(String butterfly_moth_comments) { this.butterfly_moth_comments = butterfly_moth_comments; }

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

	public int getWho_submitted_id() {
		return who_submitted_id;
	}

	public void setWho_submitted_id(int who_submitted_id) {
		this.who_submitted_id = who_submitted_id;
	}

	public int getWho_confirmed_id(){
		return this.who_confirmed_id;
	}

	public void setWho_confirmed_id(int who_confirmed_id)
	{
		this.who_confirmed_id = who_confirmed_id;
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

	public LocalDate getDate_collected() {
		return date_collected;
	}

	public void setDate_collected(LocalDate date_collected) {
		this.date_collected = date_collected;
	}

	public LocalDate getDate_generated() {
		return date_generated;
	}

	public void setDate_generated(LocalDate date_generated) {
		this.date_generated = date_generated;
	}

    public LocalDate getDate_confirmed() {
        return date_confirmed;
    }

    public void setDate_confirmed(LocalDate date_confirmed) {
        this.date_confirmed = date_confirmed;
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

	public ArrayList<Pollinator> getPollinators() {
		return pollinators;
	}

	public void setPollinators(ArrayList<Pollinator> pollinators) {
		this.pollinators = pollinators;
	}

	public ArrayList<Plant> getPlants() {
		return plants;
	}

	public void setPlants(ArrayList<Plant> plants) { this.plants = plants; }

	public ArrayList<PlantStrain> getPlantStrains() {
		return strains;
	}

	public void setPlantStrains(ArrayList<PlantStrain> plantStrains) { this.strains = strains; }

	public ArrayList<User> getGenerators() {
		return generators;
	}

	public void setGenerators(ArrayList<User> generators) {
		this.generators = generators;
	}

    public ArrayList<PollinatorVisitCount> getPollinatorVisitCounts() {
        return visitCounts;
    }

    public void setPollinatorVisitCounts(ArrayList<PollinatorVisitCount> pvc) {
        this.visitCounts = visitCounts;
    }

	public void setPlots(ArrayList<Plot> plot)
	{
		this.plots = plots;
	}

	public ArrayList<Plot> getPlots()
	{
		return this.plots;
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
