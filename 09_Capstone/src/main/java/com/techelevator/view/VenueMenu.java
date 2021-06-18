package com.techelevator.view;

import java.util.Scanner;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Venue;
import com.techelevator.model.VenueDAO;
import com.techelevator.model.jdbc.JDBCVenueDAO;
import org.apache.commons.dbcp2.BasicDataSource;

// all usage of System.out or System.in will be in this class
public class VenueMenu {


    private final Scanner in = new Scanner(System.in);
    //private VenueDAO venueDAO = new JDBCVenueDAO(dataSource);




    /**
     * main menu displayed when program runs, user chooses to see venue list or quit program
     * @return user selection of 1 or Q as a String
     */
    public void mainMenu() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("\t1) List Venues");
        System.out.println("\tQ) Quit");
    }

    public String userChoice() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    /**
     * when option 1 from mainMenu is chosen, display View Venues question
     */
    public void viewVenuesMenu() {
        //Scanner in = new Scanner(System.in);

        List<Venue> allVenues = venueDAO.getAllVenues();
        System.out.println();
        System.out.println("Which venue would you like to view?");
        System.out.println(allVenues);
    }

}
