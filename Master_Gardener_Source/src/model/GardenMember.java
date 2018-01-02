package model;

public class GardenMember {

    private int member_id, user_id, garden_id;
	
	public GardenMember(int garden_id, int user_id){
        this.setGarden_id(garden_id);
        this.setUser_id(user_id);
	}

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGarden_id() {
        return garden_id;
    }

    public void setGarden_id(int garden_id) {
        this.garden_id = garden_id;
    }
}