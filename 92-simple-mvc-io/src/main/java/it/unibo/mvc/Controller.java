package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File currentFile;
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String HOME = System.getProperty("user.home");
    private static final String DEFAULT_FILE = "output.txt";

    /**
     * Constructor of the Controller class, instantiates the specified file.
     * 
     * @param currentFile The specified file
     */
    public Controller(final File currentFile) {
        this.currentFile = currentFile;
    }

    /**
     * Constructor of the Controller class, instantiates the current file field with
     * the default file.
     */
    public Controller() {
        this(new File(HOME + SEPARATOR + DEFAULT_FILE));
    }

    /**
     * Sets the current file with the specified file.
     * 
     * @param newCurrentFile The file that wants to be set as the current file
     */
    public void setFile(final File newCurrentFile) {
        this.currentFile = newCurrentFile;
    }

    /**
     * Getter of the currentFile field.
     * 
     * @return the current file stored in the instance
     */
    public File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * Get the path of the file.
     * 
     * @return the path of the file
     */
    public String getCurrentFilePath() {
        return this.currentFile.toPath().toString();
    }

    /**
     * Writes in the file some text.
     * 
     * @param text the string that will be written in the file
     * @throws IOException if an I/O error occurs while opening or creating the file
     */
    public void writeIntoFile(final String text) throws IOException {
        final PrintStream ps = new PrintStream(this.getCurrentFilePath(), StandardCharsets.UTF_8);
        ps.print(text);
        ps.close();
    }
}
