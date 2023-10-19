/*
Name: Joseph M. Scollo
School: Suny Oswego
Course: CSC-365 Data Structures

Class: Controller
About Class: This class contains 2 methods and a constructor. The details on their members and how the function is documented
                  through out the code. This class is the controller that receives user input from the Main class and passes it to LoadPages class.

*/


import java.io.IOException;
import java.util.*;

public class Controller{
    private LoadPages l;
    private String[] errMess;
    private boolean error;

    public Controller(){
        errMess = new String[20];
        error = false;
        Arrays.fill(errMess, "Invalid URL!");
    }
    public String[] run(String url) throws IOException { // takes user url input creates a new loadPages object and executes pageLoder method.
        l = new LoadPages();
        l.pageLoader();

        try {
            l.loadUserSite(url); // passes the url entered to the loadUserSite in pageLoader class.
        } catch (Exception e) {
            System.out.println("Invalid Entry! Enter a valid url");
            error = true; // if an invalid url is entered boolean value of error is set to true.
        }
        if (error)
            return errMess; // returns an error message if error value is set to true.
        else {
            return l.getAllCosScores(); // gets scores if error is false
        }
    }
    String mostSimilar(){
        if(error)
            return "You have entered an invalid url. Please click the clear button and re-enter a valid url."; // returns an error message if error value is set to true.
        else{
            String mostSim = l.getCosineSimScore(); // gets max score if error is false
            error = false;
            return mostSim;
        }
    }

}
