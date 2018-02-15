package model;

import java.util.Random;
import java.sql.Blob;

//TODO: define parameters
public class User {
	private int userId;
	private String username = null;
	private String password = null;
	private int loginId = -1;
	private String name = null;
	private String email = null;
	private String description = null;
	private byte[] portrait;
	//private int moderator_status = 0;

	public User(String username, String password, int id, String name, String email, String description, byte[] portrait){
		this.username = username;
		this.password = password;
		this.loginId = id;
		this.name = name;
		this.email = email;
		this.description = description;
		this.portrait = portrait;
		//this.moderator_status = 0;
	}
	
	public User(){
		this.username = "";
		this.password = "";
		this.loginId = 0;
		this.name = "";
		this.email = "";
		this.description = "";
		this.portrait = new byte[1024];
		//this.moderator_status = 0;
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
	
	public String getName(){
		return this.name;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getDescription(){
		return this.description;
	}

	public byte[] getPortrait() {
		return portrait;
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
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	/*public int getModeratorStatus()
	{
		return this.moderator_status;
	}

	public void setModeratorStatus(int status)
	{
		this.moderator_status = status;
	}*/

	public void setPortrait(byte[] portrait) {
		this.portrait = portrait;
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