package com.techelevator.view;

import java.util.Scanner;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Venue;
import com.techelevator.model.VenueDAO;
import com.techelevator.model.jdbc.JDBCVenueDAO;
import org.apache.commons.dbcp2.BasicDataSource;

// all usage of System.out or System.in will be in this class
public class VenueMenu {

    private final Scanner in = new Scanner(System.in);
    private VenueDAO venueDAO;
    private Venue venue;


    public String userChoice() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    /**
     * main menu displayed when program runs, user chooses to see venue list or quit program
     */
    public void mainMenu() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("\t1) List Venues");
        System.out.println("\tQ) Quit");
    }


    /**
     * when option 1 from mainMenu is chosen, display View Venues question and take user input
     */
    public void viewVenuesMenu() {
        System.out.println();
        System.out.println("Which venue would you like to view?");
        System.out.println();
    }


    /**
     * lists venues in alphabetical order, used during display of Venues Menu
     * @param venues
     */
    public String listVenues(List<Venue> venues) {
        Scanner in = new Scanner(System.in);
        if (venues.size() > 0) {
            for (Venue venue : venues) {
                System.out.println("\t" + (venues.indexOf(venue) + 1) + ") " + venue.getName());
            }
        } else {
            System.out.println("*** No results ***");
        }
        System.out.println("\tR) Return to Previous Screen");
        String input = in.nextLine();
        return input;

    }


    /**
    * displays venue details
    */
    public void venueDetailsMenu(List<Venue> venues, String input) {
        int userChoice = Integer.parseInt(input) - 1;

        System.out.println();
        System.out.println(venues.get(userChoice).getName());
        System.out.println("Location: " + venues.get(userChoice).getCity_name() + ", " + venues.get(userChoice).getState_abbreviation());
        System.out.println("Categories: " + venues.get(userChoice).getCategories());
        System.out.println();
        System.out.println(venues.get(userChoice).getDescription());
        System.out.println();
        System.out.println("What would you like to do next?");
        System.out.println("\t1) View Spaces");
        System.out.println("\t2) Search for Reservation");
        System.out.println("\t3) Return to Previous Screen");
    }
}
