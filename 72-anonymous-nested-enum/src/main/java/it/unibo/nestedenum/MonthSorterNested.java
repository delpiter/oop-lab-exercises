package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {

        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final static List<String> MONTH_NAMES = Arrays.asList("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY",
                "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
        private final int days;

        private Month(int days) {
            this.days = days;
        }

        public int getDays() {
            return this.days;
        }

        public static Month fromString(final String month) {
            try {
                return Month.valueOf(month);
            } catch (IllegalArgumentException e) {
                List<String> months = MONTH_NAMES;
                int index = 0;

                List<String> result = new ArrayList<String>();
                while (index < months.size()) {
                    String s = months.get(index);
                    if (s.startsWith(month.toUpperCase())) {
                        result.add(s);
                    }
                    index++;
                }
                if (result.size() > 1) {
                    throw new IllegalArgumentException("The given string is ambiguos, possible matches: " + result);
                } else if (result.size() < 1) {
                    throw new IllegalArgumentException("There is no possible match to the string: " + month);
                }
                return Month.valueOf(result.iterator().next());
            }
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(final String month1, final String month2) {
                final var m1 = Month.fromString(month1);
                final var m2 = Month.fromString(month2);
                return Integer.compare(m1.days, m2.days);
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(String month1, String month2) {
                return Month.fromString(month1).compareTo(Month.fromString(month2));
            }
        };
    }
}
