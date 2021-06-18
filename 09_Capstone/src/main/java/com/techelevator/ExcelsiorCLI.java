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

			//MAIN MENU options 1 for List Venues, Q for Quit Program
			if(userChoice.equals("1")) {
				menu.viewVenuesMenu();
				List<Venue> allVenues = venueDAO.getAllVenues();
				String input = menu.listVenues(allVenues);
				menu.venueDetailsMenu(allVenues, input);

			} else if(userChoice.equalsIgnoreCase("Q")) {
				return;
			}

			//VIEW VENUES options 1,2,.. for Selecting Venue, R for Return to Previous Screen
			if(userChoice.equalsIgnoreCase("R")) {
				menu.mainMenu();
			}
		}
	}
}

