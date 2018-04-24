package database.core;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InitialData {

	public static List<GardenMember> getGardenMembers() throws IOException {
		List<GardenMember> gardenMemberList = new ArrayList<GardenMember>();
		ReadCSV readGardenMembers = new ReadCSV("mg_garden_member.csv");
		try {
			// auto-generated primary key for group members table
			Integer memberId = 1;
			while (true) {
				List<String> tuple = readGardenMembers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				GardenMember gardenMember = new GardenMember(0,0);
				gardenMember.setMember_id(memberId++);
				gardenMember.setGarden_id(Integer.parseInt(i.next()));
				gardenMember.setUser_id(Integer.parseInt(i.next()));
				gardenMemberList.add(gardenMember);
			}
			return gardenMemberList;
		} finally {
			readGardenMembers.close();
		}
	}

	public static List<Post> getPosts() throws IOException {
		List<Post> postList = new ArrayList<Post>();
		ReadCSV readPosts = new ReadCSV("mg_post.csv");
		try {
			// auto-generated primary key for posts table
			Integer postId = 1;
			while (true) {
				List<String> tuple = readPosts.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Post post = new Post(null, 0, 0);
				post.setPostId(postId++);
				post.setUser_id(Integer.parseInt(i.next()));
				post.setGroupId(Integer.parseInt(i.next()));
				post.setComments(i.next());
				postList.add(post);
			}
			return postList;
		} finally {
			readPosts.close();
		}
	}

	public static List<User> getAccounts() throws IOException {
		List<User> userList = new ArrayList<User>();
		ReadCSV readAccounts = new ReadCSV("mg_account.csv");
		try {
			// auto-generated primary key for accounts table
			Integer userId = 1;
			while (true) {
				List<String> tuple = readAccounts.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User user = new User("Egg", "12345678", userId, "memes", "memes", "memes", "memes");
				user.setUserId(userId++);
				user.setUsername(i.next());
				user.setPassword(i.next());
				user.setLoginId(Integer.parseInt(i.next()));
				user.setFirstName(i.next());
				user.setLastName(i.next());
				user.setEmail(i.next());
				user.setDescription(i.next());
				userList.add(user);
			}
			return userList;
		} finally {
			readAccounts.close();
		}
	}

	public static List<County> getCounties() throws IOException {
		List<County> countyList = new ArrayList<County>();
		ReadCSV readCounties = new ReadCSV("mg_county.csv");
		try {
			// auto-generated primary key for groups table
			Integer county_id = 1;
			while (true) {
				List<String> tuple = readCounties.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				County county = new County(county_id, "", "");
				county.setCountyID(county_id++);
				county.setCountyName(i.next());
				county.setStateName(i.next());
				countyList.add(county);
			}
			return countyList;
		} finally {
			readCounties.close();
		}
	}

	public static List<Garden> getGardens() throws IOException {
		List<Garden> gardenList = new ArrayList<Garden>();
		ReadCSV readGardens = new ReadCSV("mg_garden.csv");
		try {
			// auto-generated primary key for groups table
			Integer garden_id = 1;
			while (true) {
				List<String> tuple = readGardens.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Garden garden = new Garden(null, null);
				garden.setGarden_id(garden_id++);
				garden.setGarden_name(i.next());
				garden.setDescription(i.next());
				gardenList.add(garden);
			}
			return gardenList;
		} finally {
			readGardens.close();
		}
	}

	public static List<Pollinator> getPollinators() throws IOException {
		List<Pollinator> pollinatorList = new ArrayList<Pollinator>();
		ReadCSV readPollinators = new ReadCSV("mg_pollinator.csv");
		try {
			// auto-generated primary key for groups table
			Integer pollinator_id = 1;
			while (true) {
				List<String> tuple = readPollinators.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Pollinator pollinator = new Pollinator(0,null, null);
				pollinator.setPollinatorID(pollinator_id++);
				pollinator.setPollinatorName(i.next());
				pollinator.setPollinatorType(i.next());
				pollinatorList.add(pollinator);
			}
			return pollinatorList;
		} finally {
			readPollinators.close();
		}
	}
}
