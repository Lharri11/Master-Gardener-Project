package model;
//TODO: define parameters
public class County
{
	private int countyID;

	private String county_name, state_name;

	public County(int countyID, String county_name, String state_name)
	{
		this.countyID = countyID;
		this.county_name = county_name;
		this.state_name = state_name;
	}

	public void setCountyID(int countyID)
	{
		this.countyID = countyID;
	}
	public void setCountyName(String county_name)
	{
		this.county_name = county_name;
	}

	/**
	 *
	 * @param stateName If you aren't sure what the state is, pass it at PENNSYLVANIA (temporary punctuation, subject to change).
	 */
	public void setStateName(String state_name)
	{
		this.state_name = state_name;
	}

	public int getCountyID()
	{
		return this.countyID;
	}

	public String getCountyName()
	{
		return this.county_name;
	}

	/**
	 *
	 * @return There's a good chance this will always return PENNSYLVANIA (punctuation tentative).
	 */
	public String getStateName()
	{
		return this.state_name;
	}
}
