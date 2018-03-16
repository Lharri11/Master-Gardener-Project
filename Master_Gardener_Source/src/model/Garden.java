package model;
//TODO: define parameters
import java.util.ArrayList;

public class Garden {

    private int garden_id;
    private String garden_name, description;
	private ArrayList<User> members;
	private ArrayList<Post> posts;
	
	public Garden(String garden_name, String description){
	    this.setGarden_name(garden_name);
		this.setDescription(description);
	}

	public int getGarden_id() {
		return garden_id;
	}

	public void setGarden_id(int garden_id) {
		this.garden_id = garden_id;
	}

	public String getGarden_name() {
		return garden_name;
	}

	public void setGarden_name(String garden_name) {
		this.garden_name = garden_name;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

	public ArrayList<User> getMembers()
	{
		return this.members;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public ArrayList<Post> getPosts()
	{
		return this.posts;
	}
}
