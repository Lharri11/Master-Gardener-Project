package controller;

import javax.servlet.http.HttpServletRequest;

import database.core.*;
import model.User;
import model.ObjectHandler;

import java.sql.SQLException;

public class LoginController {

	private IDatabase database = null;

	public LoginController(){
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}

	public boolean handleLoginCheck(HttpServletRequest req){
		String user = ObjectHandler.castObject(req.getSession().getAttribute("username"));
		if(user != null){
			Integer loginId = ObjectHandler.castObject(req.getSession().getAttribute("login_id"));
			loginId = this.validateLogin(user, loginId);
			if(loginId >= 0){
				req.getSession().setAttribute("login_id", loginId);
				req.setAttribute("account", this.returnUserForUsername(user));
				req.setAttribute("loggedin", true);
				return true;
			}
		}
		req.setAttribute("loggedin", false);
		return false;
	}

	// Returns new login id
	public int validateLogin(String username, int loginId){
		int persistantLoginId = this.database.queryForLoginIdByUsername(username);
		User user = this.database.queryForUserByUsername(username);
		if(persistantLoginId == loginId && loginId != -1){
			user.createLoginId();
		}
		else{
			user.resetLoginId();
		}
		return user.getLoginId();
	}

	// Return new login id
	public int loginUser(String username, String password) throws SQLException {
		int loginId = -1;
		String salt_pass = this.database.saltPassword(username, password);
		String hash_pass = this.database.hashString(salt_pass);
		String pass = this.database.queryForPasswordByUsername(username);
		//System.out.println(pass);

		if(pass!= null && hash_pass !=null){
			if(pass.equals(hash_pass)){
				User user = this.database.queryForUserByUsername(username);
				System.out.println("User:\n" + user.getUsername());
				//System.out.println(User.getUsername() + User.getPassword());
				loginId = user.createLoginId();
			}
		}
		return loginId;
	}

	public User returnUserForUsername(String username){
		return this.database.queryForUserByUsername(username);
	}
}

