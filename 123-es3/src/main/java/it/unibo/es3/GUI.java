package it.unibo.es3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
    
    private final Map<JButton,Pair<Integer,Integer>> cells = new HashMap<>();
    
    public GUI(int width) {
        final Logics logic = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        final JPanel canva = new JPanel();
        canva.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(width,width));
        canva.add(panel,BorderLayout.CENTER);
        this.getContentPane().add(canva);
        
        JButton next = new JButton(">");
        canva.add(next,BorderLayout.SOUTH);
        
        next.addActionListener(e -> {
           logic.executeNextStep();
           this.cells.forEach((btn, position) -> btn.setText(logic.setText(position)));
           if(logic.doClose()){
                System.exit(0);
           }
        });

        for (int i=0; i<width; i++){
            for (int j = 0; j < width; j++) {
                var position = new Pair<>(j, i);
                final JButton jb = new JButton(logic.setText(position));
                panel.add(jb);
                this.cells.put(jb, position);
            }
        }
        this.setAutoRequestFocus(true);
        this.setVisible(true);
    }
    
}