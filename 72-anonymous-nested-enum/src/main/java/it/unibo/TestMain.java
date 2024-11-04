package it.unibo;

import it.unibo.nestedenum.MonthSorterNested;

public class TestMain {

    public static void main(String[] args) {
        MonthSorterNested asd = new MonthSorterNested();
        // asd.sortByOrder().compare("oct", "jan");
        System.out.println(asd.sortByOrder().compare("oct", "jan"));
    }
}