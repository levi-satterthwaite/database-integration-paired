package com.techelevator;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.jdbc.JDBCVenueDAO;
import com.techelevator.model.VenueDAO;
import com.techelevator.model.Venue;
import com.techelevator.model.jdbc.JDBCSpaceDAO;
import com.techelevator.model.SpaceDAO;
import com.techelevator.model.Space;
import com.techelevator.view.VenueMenu;

import java.util.Arrays;
import java.util.List;

// This class should control the workflow of the application, but not do any other work

public class ExcelsiorCLI {

	private VenueMenu menu;
	private VenueDAO venueDAO;
	private SpaceDAO spaceDAO;
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
		spaceDAO = new JDBCSpaceDAO(dataSource);
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
				String venueMenuInput = menu.venueDetailsMenu(allVenues, input);

				if(venueMenuInput.equals("1")) {
					int venueId = menu.venueIndexToVenueId(allVenues, input);
					List<Space> venueSpacesList = spaceDAO.getSpacesByVenue(venueId);
					menu.venueSpaces(allVenues, venueSpacesList, venueId, input);
				}

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

