package database.core;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.*;
import org.junit.Test;

public class InitialData {
	static double invalidEntry = 0.0;


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
				User user = new User(null, null, 0, null, null, null);
				user.setUserId(userId++);
				user.setUsername(i.next());
				user.setPassword(i.next());
				user.setLoginId(Integer.parseInt(i.next()));
				user.setName(i.next());
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
	public static int gardencount =0;
	// pollinater data stuff pulling from the real csv
	public static List<PollinatorDataForm> getPollinatorDataForm(String resName) throws IOException {
		List<PollinatorDataForm> pollinatorList = new ArrayList<PollinatorDataForm>();

		List<List<String>> readPollinatorData = CsvParser.ParseCSV(resName);
		gardencount ++;
		try {
			//PollinatorDataForm TestPdata = new PollinatorDataForm();
			int lineNo = 1;
//
////test the whole array to see what its contents are
//			        // the following code lets you iterate through the 2-dimensional array
//
//        for(List<String> line: readPollinatorData) {
//            int columnNo = 1;
//            for (String value: line) {
//                System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
//                columnNo++;
//            }
//            lineNo++;
//        }
			int week_num = -4;
			for(List<String> line: readPollinatorData) {
				///location for each variable in the spreadsheet array
				int date_loc = 1;
				int time_start = 2;
				int time_stop =3;
				int temp_loc = 4;
				int wind_loc = 5;
				int cloud_loc = 6;
				week_num ++;



				// make a new model each loop to push into the list to send to the data base
				PollinatorDataForm TestPdata = new PollinatorDataForm(0,0, 0,0, 0, 0,null, null,null, null,null, null, null, null, null, null, null, null);

// set the fields for the model
				if("".equals(line.get(7))){
					// do nothing row is empty
				}else if(lineNo >=5 ){

					DateTimeFormatter DATEFORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

					DateTimeFormatter DATEFORMATTER = new DateTimeFormatterBuilder().append(DATEFORMATTER1)
							.parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
							.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
							.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
							.toFormatter();

					//DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate ldt = LocalDate.parse(String.valueOf(line.get(date_loc)), DATEFORMATTER);

					TestPdata.setWeek_number(week_num);


					TestPdata.setGarden_id(gardencount); 			/// will need to change probably getter from database
					TestPdata.setCounty_id(gardencount); 			/// will need to change probably getter from database
					TestPdata.setGenerator_id(-1); 		/// will need to get from database
					TestPdata.setTemperature(Integer.parseInt(line.get(temp_loc)));
//					TestPdata.setDate_created(Date.from(Instant.line.get(date_loc)));
//					TestPdata.setDate_created(LocalDateTime.parse(line.get(date_loc)));

					TestPdata.setDate_created(ldt);
					TestPdata.setDate_generated(LocalDate.now());

					TestPdata.setMonitor_start(LocalTime.parse(line.get(time_start)));
					TestPdata.setMonitor_stop(LocalTime.parse(line.get(time_stop)));
					TestPdata.setWind_status(line.get(wind_loc));
					TestPdata.setCloud_status(line.get(cloud_loc));
					TestPdata.setComments(""); // null for 2016 not tracked
					pollinatorList.add(TestPdata);
				}

				lineNo++;
			}

			return pollinatorList;
		}
		finally {
			// this should probably do something
			// trying to clean up resources
			readPollinatorData.clear();
		}

	}

	// pollinater data stuff pulling from the real csv
	public static List<Plot> getPlotData(String resName) throws IOException {
		List<Plot> plotList = new ArrayList<Plot>();

		List<List<String>> readPollinatorData = CsvParser.ParseCSV(resName);

		try {
//		//test the whole array to see what its contents are
//			int lineNo = 1;
//			// the following code lets you iterate through the 2-dimensional array
//			for(List<String> line: readPollinatorData) {
//				int columnNo = 1;
//				for (String value: line) {
//					System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
//					columnNo++;
//				}
//				lineNo++;
//			}

			int count =0;
			int lineNo = 0;
			int plot_size_col = 17;
			int coverage_col = 18;

			for (List<String> line : readPollinatorData) {

				// make a new model each loop to push into the list to send to the data base


// set the fields for the model
				// if the filed in column 7 is empty there should b nothing for that week
				if ("".equals(line.get(7))) {
					// do nothing

				} else if (lineNo >= 5) {
					int num_strains = 5;
					for(int i=1 ; i <= num_strains; i++) {
						Plot TestPlot = new Plot(0, 0,0, 0.0,0.0, null, null, null);

						if ("".equals(line.get(coverage_col))) {
							TestPlot.setPercent_coverage(invalidEntry);
						} else {
							TestPlot.setPercent_coverage(Double.parseDouble(line.get(coverage_col)));
						}
						TestPlot.setPlot_height(0.0);
						TestPlot.setBlooms_open_status("");
						if ("".equals(line.get(plot_size_col))) {
							TestPlot.setPlot_area_dbl(invalidEntry);
						} else {
							TestPlot.setPlot_area_dbl(Double.parseDouble(line.get(plot_size_col)));
						}
						/////// get this from db method
						TestPlot.setPlant_id(1);
						TestPlot.setPlant_strain_id(i);
						TestPlot.setPlot_area_unit("");
						TestPlot.setBlooms_open_status("");
						plotList.add(TestPlot);
						//System.out.println("This is coverageCol " + coverage_col );
						coverage_col+=10;
						plot_size_col+=10;

/// new plant

						if (i == 5){
							coverage_col+=2;
							plot_size_col+=2;
							for(int j=1 ; j <= 4; j++) {
								TestPlot = new Plot(0, 0, 0.0,0.0, 0.0, null, null, null);
								//System.out.println("This is coverageCol for the 2nd plot " + coverage_col);
								if ("".equals(line.get(coverage_col))) {

									TestPlot.setPercent_coverage(invalidEntry);
								} else {
									TestPlot.setPercent_coverage(Double.parseDouble(line.get(coverage_col)));
								}
								TestPlot.setPlot_height(0.0);
								TestPlot.setBlooms_open_status("");
								if ("".equals(line.get(plot_size_col))) {
									TestPlot.setPlot_area_dbl(invalidEntry);
								} else {
									TestPlot.setPlot_area_dbl(Double.parseDouble(line.get(plot_size_col)));
								}

								TestPlot.setPlant_id(2);
								TestPlot.setPlant_strain_id(j);
								TestPlot.setPlot_area_unit("");
								TestPlot.setBlooms_open_status("");
								plotList.add(TestPlot);
								//System.out.println("This is coverageCol for the 2nd plot " + coverage_col);
								coverage_col += 10;
								plot_size_col += 10;
							}

						}

					}
					plot_size_col = 17;
					coverage_col = 18;

				}
				lineNo++;
			}

			return plotList;

		}

		finally {
			// this should probably do something
			// trying to clean up resources
			readPollinatorData.clear();
		}

	}


	public static List<Plant> getPlantName(String resName)throws IOException {
		List<Plant> plantList = new ArrayList<Plant>();

		List<List<String>> readPollinatorData = CsvParser.ParseCSV(resName);

		try {
			int lineNo = 0;
			for (List<String> line : readPollinatorData) {
				// if the first line
				if (lineNo == 0) {
					Plant TestPlant = new Plant(0, null);
					TestPlant.setPlantName(line.get(7));
					plantList.add(TestPlant);

					// make another plant
					TestPlant = new Plant(0, null);
					TestPlant.setPlantName(line.get(59));
					plantList.add(TestPlant);
				}
				lineNo++;
			}
			return plantList;
		}
		finally {
			// this should probably do something
			// trying to clean up resources
			readPollinatorData.clear();
		}

	}

	public static List<PlantStrain> getPlantStrain(String resName)throws IOException {
		List<PlantStrain> plantStrainList = new ArrayList<PlantStrain>();

		List<List<String>> readPollinatorData = CsvParser.ParseCSV(resName);
		int plant_id =1;
		try {
			int lineNo = 0;
			int strain_col_count = 7;
			for (List<String> line : readPollinatorData) {
				// if the first line
				if (lineNo == 1) {
					for (int i = 0; i <9 ; i++){
						if(i==5){
							plant_id = 2;
						}
						PlantStrain TestPlantStrain = new PlantStrain(0,0,null,null);
						TestPlantStrain.setStrainName(line.get(strain_col_count));
						TestPlantStrain.setPlantID(plant_id);
//						System.out.println("This is coverageCol for the 2nd plot " + strain_col_count);
//						System.out.println("This is coverageCol for the 2nd plot " + line.get(strain_col_count));

						// offset first value because of time start and stop
						if (strain_col_count == 7){
							strain_col_count=9;
						}
						if ( strain_col_count == 59){
							strain_col_count = 61;
						}
						strain_col_count+=10;




						plantStrainList.add(TestPlantStrain);
					}
				}
				lineNo++;
			}
			return plantStrainList;
		}
		finally {
			// this should probably do something
			// trying to clean up resources
			readPollinatorData.clear();
		}

	}
	public static int visitcount_id=0;
	public static List<PollinatorVisitCount> getPollinatorVisit(String resName)throws IOException {
		List<PollinatorVisitCount> PollinatorVisitList = new ArrayList<PollinatorVisitCount>();

		List<List<String>> readPollinatorData = CsvParser.ParseCSV(resName);
		//visitcount_id++;
		try {
			int lineNo = 0;
			int col_count = 9;
			int dataFormId = 1; 		/// county sheet ??
			int pollinator_id =1;  						/////////////////////////////////// need to get from data base !!!!!!!!!!!!!!!!!!!!!!!!!!!
			int plant_id =1;
			int strain_id = 1;

			for (List<String> line : readPollinatorData) {
				// if the first line

				if ("".equals(line.get(7))) {
					// do nothing

				} else if (lineNo >= 5) {
					visitcount_id++;
					int loop_count = 0;
					while (loop_count < 9) {
						for (int x = 0; x < 8; x++) {

							PollinatorVisitCount TestPollinatorCount = new PollinatorVisitCount(0,0,0,0,0);
							if ("".equals(line.get(col_count))) {
								// do nothing
								//System.out.println("Empty String with col count : " + col_count);
								col_count++;
								pollinator_id++;

							} else {
								//System.out.println("pollinator id : " + pollinator_id +"    Visit Count : " + Integer.parseInt(line.get(col_count)) + "    loop count : " + loop_count + "     col count : " + col_count);

								TestPollinatorCount.setVisit_count(Integer.parseInt(line.get(col_count)));
								TestPollinatorCount.setData_form_id(visitcount_id);
								TestPollinatorCount.setPlant_id(plant_id);
								TestPollinatorCount.setPollinator_id(pollinator_id);
								TestPollinatorCount.setStrain_id(strain_id);

								//System.out.println("pollinator id : " + pollinator_id +"    Visit Count : " + Integer.parseInt(line.get(col_count)) + "    loop count : " + loop_count + "     col count : " + col_count);

								PollinatorVisitList.add(TestPollinatorCount);
								col_count++;
								pollinator_id++;
							}

						}
						// bump to next strain
						if(loop_count == 4){
							//System.out.println("Special case bump loop count : " + loop_count);
							col_count +=4;
							plant_id=2;
						}else {
							//System.out.println("bump loop count : " + loop_count);
							col_count += 2;
						}
						strain_id++;
						loop_count++;

						//System.out.println("loop count too see why its not ending : " + loop_count);

						//reset id
						pollinator_id= 1;
					}

					strain_id =1;
					col_count =9;

				}
				plant_id=1;
				lineNo++;
			}

			return PollinatorVisitList;
		}
		finally {
			// this should probably do something
			// trying to clean up resources
			readPollinatorData.clear();
		}

	}

}