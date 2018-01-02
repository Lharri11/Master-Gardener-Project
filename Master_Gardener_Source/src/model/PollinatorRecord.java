package model;
//TODO: define parameters
public class PollinatorRecord
{
	private int week_number = -1;
	
	public PollinatorRecord(int week_number)
	{
		if(week_number <= 0)
		{
			throw new IllegalArgumentException("Week number must be a non-zero positive integer.");
		}
		this.week_number = week_number;
	}
	
	public int getWeekNumber()
	{
		return this.week_number;
	}
	public void setWeekNumber(int week_number)
	{
		this.week_number = week_number;
	}
}