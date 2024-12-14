package it.unibo.es2;

public interface Logics {

    /**
     * 
     * @return the string that will be displayed.
     */
    String getTextToShow(final Pair<Integer,Integer> position);

    /**
     * 
     * @return weather the application needs to close.
     */
    boolean quit();
}