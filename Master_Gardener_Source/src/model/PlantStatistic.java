package model;
//TODO: define parameters
public class PlantStatistic
{
	private int plant_height = -1, plant_size = -1, percent_coverage = -1, plot_size = -1;
	private char blooms_open, vigor;

	//TODO: Ask client what values BLOOMS_OPEN and VIGOR should be limited to.
	public PlantStatistic(int plant_height, int plant_size, int percent_coverage, int plot_size, char blooms_open,
                          char vigor)
	{
		if(plant_height <= 0 || plant_size <= 0 || percent_coverage <= 0 || plot_size <= 0)
		{
			throw new IllegalArgumentException("One (or more) of the following are less than or equal to zero:" +
					"Plant Height, Plant Size, Percent Coverage, Plot size. None of these values should be zero or negative.");
		}
        if(percent_coverage > 100)
        {
            throw new IllegalArgumentException("Percent coverage cannot be greater than 100%.");
        }
		this.plant_height = plant_height;
		this.plant_size = plant_size;
		this.percent_coverage = percent_coverage;
		this.plot_size = plot_size;
		this.blooms_open = blooms_open;
		this.vigor = vigor;
	}
	
	
	public void setPlantHeight(int plant_height)
	{
		this.plant_height = plant_height;
	}
	
	public void setPlantSize(int plantSize)
	{
		this.plant_size = plantSize;
	}
	
	/**
	 * 
	 * @param percentageCovered The percent covered, as indicated as an INTEGER on the data sheet.
	 */
	public void setPercentCoverage (int percentageCovered)
	{
		this.percent_coverage = percentageCovered;
	}
		
	/**
	 * 
	 * @param blooms_open This is typically a 1 character entry found on the data sheets. Contact system admin (Alex)
	 * in the event of any issues.
	 */
	public void setBloomsOpen (char blooms_open)
	{
		this.blooms_open = blooms_open;
	}
	
	/**
	 * 
	 * @param vigor This is typically a 1 character entry found on the data sheets. Contact system admin (Alex)
	 * in the event of any issues.
	 */
	public void setVigor (char vigor)
	{
		this.vigor = vigor;
	}
	
	public int getPlantHeight()
	{
		return this.plant_height;
	}
	
	public int getPlantSize()
	{
		return this.plant_size;
	}
	
	public int getPercentCoverage()
	{
		return this.percent_coverage;
	}
	
	public char getBloomsOpen()
	{
		return this.blooms_open;
	}
	
	public char getVigor()
	{
		return this.vigor;
	}
	
	public void setPlotSize(int plot_size)
	{
		this.plot_size = plot_size;
	}

	public int getPlotSize()
    {
        return this.plot_size;
    }
}