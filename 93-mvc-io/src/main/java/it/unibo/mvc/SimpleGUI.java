package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String TITLE = "93-mvc-io";
    private final JFrame frame = new JFrame(TITLE);
    private static final int PROPORTION = 3;
    private final Controller controller = new SimpleController();

    /**
     * Builds the UI.
     */
    public SimpleGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        final JTextField writeCommand = new JTextField();
        canvas.add(writeCommand, BorderLayout.NORTH);
        writeCommand.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(final DocumentEvent e) {
                controller.setNextPrint(writeCommand.getText());
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                controller.setNextPrint(writeCommand.getText());
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
            }

        });
        final JTextArea historyTA = new JTextArea();
        canvas.add(historyTA, BorderLayout.CENTER);
        final JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.X_AXIS));
        canvas.add(bottomRow, BorderLayout.SOUTH);
        final JButton print = new JButton("Print");
        bottomRow.add(print);
        print.addActionListener(e -> controller.print());
        final JButton showHistory = new JButton("Show History");
        bottomRow.add(showHistory);
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder sb = new StringBuilder();
                // controller.getPrintHistory().forEach(sb::append); works, but does not have a
                // new line and looks ugly
                controller.getPrintHistory().forEach(x -> sb.append(x).append('\n'));
                historyTA.setText(sb.toString());
            }

        });
    }

    /* copied from old exercises */
    private void display() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         * //
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        // .pack();
        frame.setVisible(true);
    }

    /**
     * Used to run the application.
     * 
     * @param args arguments
     */
    public static void main(final String[] args) {
        new SimpleGUI().display();
    }
}
