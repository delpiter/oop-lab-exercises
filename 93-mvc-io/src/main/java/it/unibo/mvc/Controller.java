package it.unibo.mvc;

import java.util.List;

/**
 * An interface that models a simple controller responsible of I/O access.
 */
public interface Controller {

    /**
     * A method for setting the next string to print. Null values are not
     * acceptable, and an exception should be produced.
     * 
     * @param nextString the string that needs to be set
     */
    void setNextPrint(String nextString);

    /**
     * A method for getting the next string to print.
     * 
     * @return the next string to print
     */
    String getNextPrint();

    /**
     * A method for getting the history of the printed strings (in form of a List of
     * Strings).
     * 
     * @return a list of string
     */
    List<String> getPrintHistory();

    /**
     * A method that prints the current string.
     * 
     * @throws IllegalStateException If the current string is unset
     */
    void print();
}
