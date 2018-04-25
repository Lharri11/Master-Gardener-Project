package model;

import java.util.Random;
import java.sql.Blob;

//TODO: define parameters
public class User {
	private int userId;
	private String username = null;
	private String password = null;
	private int loginId = -1;
	private String first_name = null;
	private String last_name = null;
	private String email = null;
	private String description = null;
	private int moderator_status = 0;

	public User(String username, String password, int id, String first_name, String last_name, String email, String description){

		this.username = username;
		this.password = password;
		this.loginId = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.description = description;
		this.moderator_status = moderator_status;
	}

	public User(){
		this.username = "";
		this.password = "";
		this.loginId = 0;
		this.first_name = "";
		this.last_name = "";
		this.email = "";
		this.description = "";
		this.moderator_status = 0;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername(){
		return this.username;
	}

	public String getPassword(){
		return this.password;
	}

	public int getLoginId(){
		return this.loginId;
	}

	public String getFirstName(){
		return this.first_name;
	}

	public String getLastName(){
		return this.last_name;
	}

	public String getEmail(){
		return this.email;
	}

	public String getDescription(){
		return this.description;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUsername(String user){
		this.username = user;
	}

	public void setPassword(String pass){
		this.password = pass;
	}

	public void setLoginId(int id){
		this.loginId = id;
	}

	public void setFirstName(String name){
		this.first_name = name;
	}

	public void setLastName(String name){
		this.last_name = name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public int getModeratorStatus()
	{
		return this.moderator_status;
	}

	public void setModeratorStatus(int status)
	{
		this.moderator_status = status;
	}

	/*
	 * -------------------------HELPER METHODS----------------------------
	 */

	public int createLoginId() {
		Random r = new Random();
		this.loginId = r.nextInt(99999999);
		return this.loginId;
	}

	public int resetLoginId(){
		this.loginId = -1;
		return this.loginId;
	}

	/*
	 * -----------------------VALIDATION METHODS----------------------------
	 */

}
