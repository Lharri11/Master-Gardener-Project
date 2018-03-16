package model;
//TODO: define parameters
public class Post {
		
	private int postId;
	private int user_id;
	private int groupId;
	private String comments;
	
	public Post(String comments, int accountID, int groupID){
		this.comments = comments;
		this.user_id = accountID;
		this.groupId = groupID;
	}
	
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public void setUser_id(int user_id){
		this.user_id = user_id;
	}
	
	public void setGroupId(int groupId){
		this.groupId = groupId;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}
	
	public int getPostId() {
		return postId;
	}
	
	public int getUser_id(){
		return this.user_id;
	}
	
	public int getGroupId(){
		return this.groupId;
	}
	
	public String getComments(){
		return this.comments;
	}	
}