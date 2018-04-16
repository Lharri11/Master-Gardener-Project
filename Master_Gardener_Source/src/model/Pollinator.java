package model;
//TODO: define parameters
public class Pollinator
{
	private int pollinator_id;
	
	private String pollinatorName;
	private String pollinatorType;
	
	public Pollinator(String pollinator_name, String pollinator_type)
	{
		this.pollinatorName = pollinator_name;
		this.pollinatorType = pollinator_type;
	}

	public void setPollinatorID(int pollinator_id)
	{
		this.pollinator_id = pollinator_id;
	}
	public void setPollinatorName(String pollinatorname)
	{
		this.pollinatorName = pollinatorname;
	}
	public void setPollinatorType(String pollinatortype)
	{
		this.pollinatorType = pollinatortype;
	}
	
	public int getPollinatorID()
	{
		return this.pollinator_id;
	}
	public String getPollinatorName()
	{
		return this.pollinatorName;
	}
	public String getPollinatorType()
	{
		return this.pollinatorType;
	}
	
}