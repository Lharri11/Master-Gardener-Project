package model;

public class Plot {

    private int plot_id, garden_id, plant_id, strain_id;
    private double plot_height, plot_area_dbl, percent_coverage;
    private String blooms_open_status;

    public Plot(int garden_id, int plant_id, int strain_id, double plot_height, double plot_area_dbl, double percent_coverage, String blooms_open_status)
    {
        this.garden_id = garden_id;
        this.plant_id = plant_id;
        this.strain_id = strain_id;
        this.plot_height = plot_height;
        this.plot_area_dbl = plot_area_dbl;
        this.percent_coverage = percent_coverage;
        this.blooms_open_status = blooms_open_status;
    }

    public int getPlot_id() {
        return plot_id;
    }

    public void setPlot_id(int plot_id) {
        this.plot_id = plot_id;
    }

    public int getGarden_id() {
        return garden_id;
    }

    public void setGarden_id(int garden_id) {
        this.garden_id = garden_id;
    }

    public int getStrain_id() {
        return strain_id;
    }

    public void setStrain_id(int strain_id) {
        this.strain_id = strain_id;
    }

    public int getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(int plant_id) {
        this.plant_id = plant_id;
    }

    public double getPlot_height() {
        return plot_height;
    }

    public void setPlot_height(double plot_height) {
        this.plot_height = plot_height;
    }

    public double getPlot_area_dbl() {
        return plot_area_dbl;
    }

    public void setPlot_area_dbl(double plot_area_dbl) {
        this.plot_area_dbl = plot_area_dbl;
    }

    public double getPlot_percent_coverage() {
        return percent_coverage;
    }

    public void setPlot_percent_coverage(double percent_coverage) {
        this.percent_coverage = percent_coverage;
    }

    public String getPlot_blooms_open_status() {
        return blooms_open_status;
    }

    public void setPlot_blooms_open_status(String blooms_open_status) {
        this.blooms_open_status = blooms_open_status;
    }
}