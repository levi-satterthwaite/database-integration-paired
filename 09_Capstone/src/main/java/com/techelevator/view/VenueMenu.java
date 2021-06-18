package com.techelevator.view;

import java.util.Locale;
import java.util.Scanner;
import java.util.List;
import java.time.Month;

import com.techelevator.model.Venue;
import com.techelevator.model.VenueDAO;
import com.techelevator.model.Space;
import com.techelevator.model.SpaceDAO;


// all usage of System.out or System.in will be in this class
public class VenueMenu {

    private final Scanner in = new Scanner(System.in);
    private VenueDAO venueDAO;
    private Venue venue;
    private SpaceDAO spaceDAO;
    private Space space;


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
    public String venueDetailsMenu(List<Venue> venues, String input) {
        Scanner in = new Scanner(System.in);
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
        String venuesInput = in.nextLine();
        return venuesInput;
    }

    public int venueIndexToVenueId(List<Venue> venues, String input) {
        int userChoice = Integer.parseInt(input) - 1;
        return venues.get(userChoice).getId();
    }

    /**
     * displays spaces available for venue that was selected
     */
    public void venueSpaces(List<Venue> venues, List<Space> spaces, int venueId, String input) {
        int userChoice = Integer.parseInt(input) - 1;

        System.out.println();
        System.out.println(venues.get(userChoice).getName());
        System.out.println();
        System.out.printf("%-28s %-10s %-12s %-15s %-10s %n", "\tName", "Open", "Close", "Daily Rate", "Max. Occupancy");
        if (spaces.size() > 0) {
            for (Space space : spaces) {
                 String getOpenFrom = space.getOpen_from();
                 String getOpenTo = space.getOpen_to();

                if (getOpenTo.equals("NA")) {
                    getOpenTo = "    ";
                } else {
                    getOpenTo = Month.of(Integer.parseInt(space.getOpen_to())).name().toLowerCase();
                }
                if (getOpenFrom.equals("NA")) {
                    getOpenFrom = "    ";

                } else {
                    getOpenFrom = Month.of(Integer.parseInt(space.getOpen_from())).name().toLowerCase();
                }

                String fromFirstLtr = getOpenFrom.substring(0, 1);
                String fromRestLtr = getOpenFrom.substring(1, getOpenFrom.length());
                String toFirstLtr = getOpenTo.substring(0, 1);
                String toRestLtr = getOpenTo.substring(1, getOpenTo.length());
                fromFirstLtr = fromFirstLtr.toUpperCase();
                toFirstLtr = toFirstLtr.toUpperCase();
                getOpenFrom = fromFirstLtr + fromRestLtr;
                getOpenTo = toFirstLtr + toRestLtr;

                System.out.printf("%-3s %-27s %-10s %-12s %-15s %-10s %n", ("#" + (spaces.indexOf(space) + 1)), space.getName(), getOpenFrom, getOpenTo, ("$" + space.getDaily_rate()), space.getMax_occupancy());
            }
        } else {
            System.out.println("*** No results ***");
        }

        System.out.println();
        System.out.println("What would you like to do next?");
        System.out.println("\t1) Reserve a Space");
        System.out.println("\tR) Return to Previous Screen");
    }
}
