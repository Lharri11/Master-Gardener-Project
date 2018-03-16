package model;
//TODO: define parameters
public class County
{
	private int countyID;
	
	private String countyName, stateName;
	
	public County(int countyID, String countyName, String stateName)
	{
        this.countyID = countyID;
		this.countyName = countyName;
		this.stateName = stateName;
	}
	
	public void setCountyID(int countyID)
	{
		this.countyID = countyID;
	}
	public void setCountyName(String countyName)
	{
		this.countyName = countyName;
	}
	
	/**
	 * 
	 * @param stateName If you aren't sure what the state is, pass it at PENNSYLVANIA (temporary punctuation, subject to change).
	 */
	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}
	
	public int getCountyID()
	{
		return this.countyID;
	}
	
	public String getCountyName()
	{
		return this.countyName;
	}
	
	/**
	 * 
	 * @return There's a good chance this will always return PENNSYLVANIA (punctuation tentative).
	 */
	public String getStateName()
	{
		return this.stateName;
	}
}
