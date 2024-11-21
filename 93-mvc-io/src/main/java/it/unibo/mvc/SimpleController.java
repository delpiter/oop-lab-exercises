package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of the Controller Interface.
 *
 */
public final class SimpleController implements Controller {

    private String nextPrint;
    private final List<String> history;
    private static final Logger LOGGER = Logger.getLogger("SimpleController");

    /**
     * Initializes the history of prints.
     */
    public SimpleController() {
        this.history = new LinkedList<>();
    }

    @Override
    public void setNextPrint(final String nextString) {
        this.nextPrint = Objects.requireNonNull(nextString);
    }

    @Override
    public String getNextPrint() {
        return this.nextPrint;
    }

    @Override
    public List<String> getPrintHistory() {
        return List.copyOf(history);
    }

    @Override
    public void print() {
        if (this.nextPrint == null) {
            throw new IllegalStateException("There is no string to print");
        }
        LOGGER.log(Level.INFO, nextPrint);
        this.history.add(nextPrint);
    }

}
