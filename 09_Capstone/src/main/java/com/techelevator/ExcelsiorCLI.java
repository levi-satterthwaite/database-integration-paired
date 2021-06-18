package com.techelevator;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.jdbc.JDBCVenueDAO;
import com.techelevator.model.VenueDAO;
import com.techelevator.model.Venue;
import com.techelevator.view.VenueMenu;

import java.util.Arrays;
import java.util.List;

// This class should control the workflow of the application, but not do any other work

public class ExcelsiorCLI {

	private VenueMenu menu;
	private VenueDAO venueDAO;
	private JDBCVenueDAO jdbcVenueDAO;

	private static final String MAIN_MENU_OPTION_VENUES = "List Venues";
	private static final String MAIN_MENU_OPTION_QUIT = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = new String[]{MAIN_MENU_OPTION_VENUES, MAIN_MENU_OPTION_QUIT};

	public String venueName = "";

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior_venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(BasicDataSource dataSource) {
		this.menu = new VenueMenu();

		// create your DAOs here
		venueDAO = new JDBCVenueDAO(dataSource);
	}


	public void run() {

			menu.mainMenu();

			while(true) {

			String userChoice = menu.userChoice();

			if(userChoice.equals("1")) {
				menu.viewVenuesMenu();
				// display list of venues from DAO

			} else if(userChoice.equals("Q")) {
				return;
			}
	}


//	public void listVenues(List<Venue> venues){
//		if(venues.size() > 0) {
//			for(Venue venue : venues) {
//				venueName = (venue.getName());
//			}
//		} else {
//			venueName = ("*** No results ***");
//		}
	}
}

