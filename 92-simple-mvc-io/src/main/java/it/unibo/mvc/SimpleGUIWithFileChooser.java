package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "A simple GUI application";
    private static final int PROPORTION = 3;
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();

    /**
     * Builds the swing application and creates all the needed.
     */
    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel upperView = new JPanel();
        upperView.setLayout(new BorderLayout());
        canvas.add(upperView, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setText(controller.getCurrentFilePath());
        upperView.add(textField, BorderLayout.CENTER);
        final JButton browseButton = new JButton("Browse");
        upperView.add(browseButton, BorderLayout.EAST);
        final JButton saveButton = new JButton("Save");
        canvas.add(saveButton, BorderLayout.SOUTH);
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeIntoFile(textArea.getText());
                } catch (IOException ioE) {
                    JOptionPane.showMessageDialog(frame, ioE, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                switch (chooser.showSaveDialog(frame)) {
                    case JFileChooser.APPROVE_OPTION:
                        controller.setFile(chooser.getSelectedFile());
                        textField.setText(controller.getCurrentFilePath());
                        break;

                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "An Error has occurred while choosing the file");
                        break;
                }
            }

        });
        frame.setContentPane(canvas);
    }

    private void display() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
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
     * The method to use to run the application.
     * 
     * @param args arguments
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}
