package model;

public class Plot {

    private int plot_id, plant_id, plant_strain_id;
    private double plot_height, plot_area_dbl, percent_coverage;
    private String plot_area_unit;
    private String blooms_open_status, plant_vigor_status;

    public Plot(int plot_id, int plant_id, double plot_height, double percent_coverage, double plot_area_dbl, String plot_area_unit, String blooms_open_status, String plant_vigor_status)
    {
        this.plot_id = plot_id;
        this.plant_id = plant_id;
        this.plot_height = plot_height;
        this.plot_area_dbl = plot_area_dbl;
        this.percent_coverage = percent_coverage;
        this.plot_area_unit = plot_area_unit;
        this.blooms_open_status = blooms_open_status;
        this.plant_vigor_status = plant_vigor_status;
        //this.plant_strain_id = plant_strain_id;
    }

    public int getPlot_id() {
        return plot_id;
    }

    public void setPlot_id(int plot_id) {
        this.plot_id = plot_id;
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

    public double getPercent_coverage() {
        return percent_coverage;
    }

    public void setPercent_coverage(double percent_coverage) {
        this.percent_coverage = percent_coverage;
    }

    public String getPlot_area_unit() {
        return plot_area_unit;
    }

    public void setPlot_area_unit(String plot_area_unit) {
        this.plot_area_unit = plot_area_unit;
    }

    public String getBlooms_open_status() {
        return blooms_open_status;
    }

    public void setBlooms_open_status(String blooms_open_status) {
        this.blooms_open_status = blooms_open_status;
    }

    public String getPlant_vigor_status() {
        return plant_vigor_status;
    }

    public void setPlant_vigor_status(String plant_vigor_status) {
        this.plant_vigor_status = plant_vigor_status;
    }

    public int getPlant_strain_id() {
        return plant_strain_id;
    }

    public void setPlant_strain_id(int plant_strain_id) {
        this.plant_strain_id = plant_strain_id;
    }
}